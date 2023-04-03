package com.pcPartMaker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcPartMaker.exception.ResourceNotFoundException;
import com.pcPartMaker.model.User;
import com.pcPartMaker.repository.UserRepository;


@Service
public class PCPartService {
	@Autowired
	private  UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> getUser(Long userId) 
			throws ResourceNotFoundException{
		Optional<User> user = userRepository.findById(userId);
		return user;
	}

	public User updateUser(User user, User updatedUser) {
		user.setEmail(updatedUser.getEmail());
		user.setUsername(updatedUser.getUsername());
		user.setPassword(updatedUser.getPassword());
		updatedUser = userRepository.save(user);
		return updatedUser;
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}
}

