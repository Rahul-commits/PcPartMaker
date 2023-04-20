package com.pcPartMaker.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  com.pcPartMaker.exception.TokenRefreshException;
import com.pcPartMaker.model.ERole;
import com.pcPartMaker.model.RefreshToken;
import com.pcPartMaker.model.Role;
import com.pcPartMaker.model.User;
import com.pcPartMaker.payload.request.LoginRequest;
import com.pcPartMaker.payload.request.SignupRequest;
import com.pcPartMaker.payload.response.JwtResponse;
import com.pcPartMaker.payload.response.MessageResponse;
import com.pcPartMaker.payload.response.TokenRefreshResponse;
import com.pcPartMaker.security.jwt.JwtUtils;
import com.pcPartMaker.security.services.RefreshTokenService;
import com.pcPartMaker.security.services.UserDetailsImpl;
import com.pcPartMaker.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private UserService userService;

	@Autowired
	RefreshTokenService refreshTokenService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		//String jwt = jwtUtils.generateJwtToken(userDetails);

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

		//		return ResponseEntity.ok(
		//				(new JwtResponse(jwt, userDetails.getId(),
		//				userDetails.getUsername(), userDetails.getEmail(), roles));

		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
				.body(new JwtResponse( jwtCookie.toString(), userDetails.getId(),
						userDetails.getUsername(),
						userDetails.getEmail(),
						roles));
	}








	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = userService.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = userService.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = userService.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		roles.stream().forEach(role -> user.addRoles(role));
		userService.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principle.toString() != "anonymousUser") {      
			Long userId = ((UserDetailsImpl) principle).getId();
			refreshTokenService.deleteByUserId(userId);
		}

		ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
		ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();

		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
		String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

		if ((refreshToken != null) && (refreshToken.length() > 0)) {
			return refreshTokenService.findByToken(refreshToken)
					.map(refreshTokenService::verifyExpiration)
					.map(RefreshToken::getUser)
					.map(user -> {
						ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

						return ResponseEntity.ok()
								.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
								//.body(new MessageResponse("Token is refreshed successfully!"));
								.body(new TokenRefreshResponse(jwtCookie.toString() , user.getRoles().stream().map(
										(Role role) -> role.getName().toString()).collect(Collectors.toList()), user.getUsername()));
					})
					.orElseThrow(() -> new TokenRefreshException(refreshToken,
							"Refresh token is not in database!"));
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token is empty!"));
	}

}
//	@PostMapping("register")
//	public ResponseEntity<User> createNewUser(@RequestBody User user) 
//			throws URISyntaxException, IOException, InterruptedException {
//
//		if (null != user && null != user.getUsername() && null != user.getPassword()) {
//			Integer noOfUser = userService.getUserByUsername(user.getUsername());
//			if (null != noOfUser && noOfUser != 0) {
//				return new ResponseEntity<User>(HttpStatus.CONFLICT);
//			}
//		}
//		user.setRole("user");
//		user = userService.save(user);
//
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}
//
//	@PostMapping("auth")
//	public ResponseEntity<User> authenticateUser(@RequestBody User user) 
//			throws URISyntaxException, IOException, InterruptedException {
//		User userResp = null;
//		if (null != user && null != user.getUsername() && null != user.getPassword()) {
//			userResp = userService.getUser(user.getUsername(), user.getPassword());
//			if (null == userResp) {
//				return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
//			}
//		}
//
//		return new ResponseEntity<User>(userResp, HttpStatus.OK);
//	}
//}

