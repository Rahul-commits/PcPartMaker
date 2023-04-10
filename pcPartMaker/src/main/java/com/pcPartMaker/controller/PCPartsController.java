package com.pcPartMaker.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcPartMaker.exception.ResourceNotFoundException;
import com.pcPartMaker.model.ERole;
import com.pcPartMaker.model.Role;
import com.pcPartMaker.model.User;
import com.pcPartMaker.service.PCPartService;
import com.pcPartMaker.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")

public class PCPartsController {

	@Autowired
	private PCPartService pCPartService;
	@Autowired
	private UserService userService;
	/**
	 * Get all users list from api.
	 *
	 * @return the list
	 */
	@GetMapping("/users")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> getUsers() {
		try {
			List<User> users = pCPartService.getAllUsers();

			if(null == users || users.size()==0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets users by id.
	 *
	 * @param userId the user id
	 * @return the users by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		User user = pCPartService.getUser(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/**
	 * Create user user.
	 *
	 * @param user the user
	 * @return the user
	 */
	@PostMapping("/users")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<User>createUser(@Valid @RequestBody User user) {
		try {
			if(null == user) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			Set<Role> roles = new HashSet<>();
			Role userRole = userService.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
			
			for (Role role : roles) {
				user.addRoles(role);
			}
			user = pCPartService.addUser(user);

			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update user response entity.
	 *
	 * @param userId the user id
	 * @param userDetails the user details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<User> updateUser(
			@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
					throws ResourceNotFoundException {
		User user =
				pCPartService.getUser(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		User updatedUser = pCPartService.updateUser(user, userDetails);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	/**
	 * Delete user map.
	 *
	 * @param userId the user id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
		User user = pCPartService.getUser(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

		Map<String, Boolean> response = new HashMap<>();
		pCPartService.deleteUser(user);
		response.put("deleted", Boolean.TRUE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
