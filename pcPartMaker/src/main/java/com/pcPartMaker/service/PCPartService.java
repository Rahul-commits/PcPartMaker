package com.pcPartMaker.service;

import java.util.List;

import com.pcPartMaker.model.*;
import com.pcPartMaker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PCPartService {
	@Autowired
	private  UserRepository userRepository;

	@Autowired
	private ComponentRepository componentRepository;

	@Autowired
	private MotherboardRepository motherboardRepository;

	@Autowired
	private GraphicsCardRepository graphicsCardRepository;

	@Autowired
	private CpuRepository cpuRepository;

	@Autowired
	private GpuManufacturerRepository gpuManufacturerRepository;

	@Autowired
	private CpuManufacturerRepository cpuManufacturerRepository;

	@Autowired
	private CpuSocketRepository cpuSocketRepository;

	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	// builds and create a new component. Needs to be executed for every component in the database;
	public Component createComponent(float rating, double price){
		return componentRepository.save(Component.builder().rating(rating).price(price).build());
	}

	// MOTHERBOARD CRUD
	public Motherboard createMotherboard(Motherboard motherboard){
		Component component = componentRepository.getReferenceById(motherboard.getComponent().getComponentId());
		motherboard.setComponent(component);
		return motherboardRepository.save(motherboard);
	}


	public List<Motherboard> getAll() {
		return motherboardRepository.findAll();
	}
	public void deleteMotherboard(String id){
		motherboardRepository.deleteById(id);
	}

	//GRAPHICS CARD CRUD
	public GraphicsCard createGraphicsCard(GraphicsCard graphicsCard){
		Component component = componentRepository.getReferenceById(graphicsCard.getComponent().getComponentId());
		GpuManufacturer manufacturer = gpuManufacturerRepository.getReferenceById(graphicsCard.getGpuManufacturer().getManufacturerName());
		graphicsCard.setComponent(component);
		graphicsCard.setGpuManufacturer(manufacturer);
		return graphicsCardRepository.save(graphicsCard);
	}

	public void deleteGraphicsCard(String graphicsCard){
		graphicsCardRepository.deleteById(graphicsCard);
	}

	public List<GraphicsCard> getAllGraphicsCards(){
		return graphicsCardRepository.findAll();
	}

	// CPU CRUD

	public CPU createCpu(CPU cpu){
		Component component = componentRepository.getReferenceById(cpu.getComponent().getComponentId());
		CpuManufacturer cpuManufacturer = cpuManufacturerRepository.getReferenceById(cpu.getCpuManufacturer().getManufacturerName());
		CpuSocketType cpuSocketType = cpuSocketRepository.getReferenceById(cpu.getCpuSocketType().getSocket());
		cpu.setComponent(component);
		cpu.setCpuManufacturer(cpuManufacturer);
		cpu.setCpuSocketType(cpuSocketType);
		return cpuRepository.save(cpu);
	}

	public void deleteCpu(String cpuId){
		cpuRepository.deleteById(cpuId);
	}

	public List<CPU> findAllCpus(){
		return cpuRepository.findAll();
	}







	// TODO
	/*
	public CPU createCpu();
	public Motherboard createMotherboard();
	public GraphicsCard createGraphicsCard();
	public ramKit createRamKit();
	*/

}
