package com.pcPartMaker.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcPartMaker.model.ERole;
import com.pcPartMaker.model.Role;
import com.pcPartMaker.model.User;
import com.pcPartMaker.repository.RoleRepository;
import com.pcPartMaker.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private  UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

//	public Integer getUserByUsername(String username) {
//		return userRepository.getUserCount(username);
//	}
//
//	public User getUser(String username, String password) {
//		return userRepository.getUser(username, password);
//	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public Optional<Role> findByName(ERole roleUser) {
		return roleRepository.findByName(roleUser);
	}
}
