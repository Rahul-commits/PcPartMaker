package com.pcPartMaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcPartMaker.model.User;
import com.pcPartMaker.repository.UserRepository;

@Service
public class PCPartService {
	@Autowired
	private  UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}
}
