package com.pcPartMaker.controller;
import org.springframework.security.authentication.AuthenticationManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pcPartMaker.model.ERole;
import com.pcPartMaker.model.RefreshToken;
import com.pcPartMaker.model.Role;
import com.pcPartMaker.model.User;
import com.pcPartMaker.payload.request.LoginRequest;
import com.pcPartMaker.payload.request.SignupRequest;
import com.pcPartMaker.payload.request.TokenRefreshRequest;
import com.pcPartMaker.payload.response.JwtResponse;
import com.pcPartMaker.payload.response.MessageResponse;
import com.pcPartMaker.payload.response.TokenRefreshResponse;
import com.pcPartMaker.security.jwt.JwtUtils;
import com.pcPartMaker.security.services.UserDetailsImpl;
import com.pcPartMaker.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import com.pcPartMaker.security.services.RefreshTokenService;

import  com.pcPartMaker.exception.TokenRefreshException;

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

		String jwt = jwtUtils.generateJwtToken(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
				userDetails.getUsername(), userDetails.getEmail(), roles));
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

		user.setRoles(roles);
		userService.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping("/refreshtoken")
	  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
	    String requestRefreshToken = request.getRefreshToken();

	    return refreshTokenService.findByToken(requestRefreshToken)
	        .map(refreshTokenService::verifyExpiration)
	        .map(RefreshToken::getUser)
	        .map(user -> {
	          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
	          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken,user.getRoles()));
	        })
	        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
	            "Refresh token is not in database!"));
	  }
	  
	  @PostMapping("/signout")
	  public ResponseEntity<?> logoutUser() {
	    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Long userId = userDetails.getId();
	    refreshTokenService.deleteByUserId(userId);
	    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
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

