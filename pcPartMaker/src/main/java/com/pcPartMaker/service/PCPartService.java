package com.pcPartMaker.service;

import java.util.List;

import com.pcPartMaker.model.CPU;
import com.pcPartMaker.model.Component;
import com.pcPartMaker.model.GraphicsCard;
import com.pcPartMaker.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcPartMaker.model.User;
import com.pcPartMaker.repository.UserRepository;

@Service
public class PCPartService {
	@Autowired
	private  UserRepository userRepository;

	@Autowired
	private ComponentRepository componentRepository;

	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}


	// builds and create a new component. Needs to be executed for every component in the database;
	public Component createComponent(float rating, double price){
		return componentRepository.save(Component.builder().rating(rating).price(price).build());
	}


	// TODO
	/*
	public CPU createCpu();
	public Motherboard createMotherboard();
	public GraphicsCard createGraphicsCard();
	public ramKit createRamKit();
	*/

}
