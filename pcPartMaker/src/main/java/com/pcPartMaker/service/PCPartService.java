package com.pcPartMaker.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.pcPartMaker.model.*;
import com.pcPartMaker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcPartMaker.exception.ResourceNotFoundException;

import javax.swing.text.html.Option;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class PCPartService {
	@Autowired
	private  UserRepository userRepository;

	@Autowired
	private ComponentRepository componentRepository;

	@Autowired
	private MotherboardRepository motherboardRepository;

	@Autowired
	private RamKitRepository ramKitRepository;

	@Autowired
	//remove
	PCIExpressSlotTypeRepository pCIExpressSlotTypeRepository;
	@Autowired
	MemoryTypeRepository memoryTypeRepository;
	@Autowired
	CpuSocketRepository cpuSocketRepository;
	@Autowired
	DimmSlotTypeRepository dimmSlotTypeRepository;

	@Autowired
	CpuRepository cpuRepository;

	@Autowired
	PCConfigurationRepository pcConfigurationRepository;

	@Autowired
	GraphicsCardRepository graphicsCardRepository;

	@Autowired
	MotherboardAndPCISlotRepository motherboardAndPCISlotRepository;

	@Autowired
	CpuManufacturerRepository cpuManufacturerRepository;

	@Autowired
	GpuManufacturerRepository gpuManufacturerRepository;

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

	//Create Component
	// builds and create a new component. Needs to be executed for every component in the database;
	public Component createComponent(float rating, double price){
		return componentRepository.save(Component.builder().rating(rating).price(price).build());
	}

	// MOTHERBOARD CRUD
	public MotherboardJson createMotherboard(MotherboardJson motherboardJson){
			int modelNumber = motherboardJson.getModelNumber();
			String productName= motherboardJson.getProductName();
			boolean eccCompatibility = motherboardJson.isEccCompatibility();
			int wattage = motherboardJson.getWattage();

			Optional<DimmSlotType> dimmSlotType = dimmSlotTypeRepository.findBySlotTypeId(motherboardJson.getDimmSlotTypeId());
			Optional<MemoryType> memoryType = memoryTypeRepository.findByMemoryTypeId(motherboardJson.getMemoryTypeId());

			Optional<PciExpressSlotType> pciExpressSlotType = pCIExpressSlotTypeRepository.findByGeneration(motherboardJson.getPciGeneration());
			Optional<CpuSocketType> cpuSocketType = cpuSocketRepository.findBySocket(motherboardJson.getSocket());
			Component component = createComponent(motherboardJson.getRating(), motherboardJson.getPrice());

			MotherboardAndPciSlot motherboardPCIslots = new MotherboardAndPciSlot();
			motherboardPCIslots.setQuantity(motherboardJson.getQuantity());
			motherboardPCIslots.setSlotType(pciExpressSlotType.get());

			Motherboard motherboard = motherboardRepository.save(new
					Motherboard(modelNumber, productName,eccCompatibility, wattage, dimmSlotType.get(),
					memoryType.get(), component, cpuSocketType.get(), motherboardPCIslots));

			Integer pciSlot = 0;
			Set<MotherboardAndPciSlot> motherboardAndPciSlotSet = motherboard.getMotherboardPCIslots();
			for (MotherboardAndPciSlot motherboardAndPciSlot : motherboardAndPciSlotSet) {
				motherboardJson.setId(motherboardAndPciSlot.getId());
				break;
			}
		return motherboardJson;
	}

	public List<MotherboardJson> getAllMotherboards() {
		List<Motherboard> motherboardList = motherboardRepository.findAll();
		return createMbResponse(motherboardList);
	}

	private List<MotherboardJson> createMbResponse(List<Motherboard> motherboardList) {
		List<MotherboardJson> motherboardJsonList = new ArrayList<>();
		for (Motherboard motherboard : motherboardList) {
			Set<MotherboardAndPciSlot> motherboardAndPciSlotSet = motherboard.getMotherboardPCIslots();
			for (MotherboardAndPciSlot motherboardAndPciSlot : motherboardAndPciSlotSet) {
				MotherboardJson motherboardJson = new MotherboardJson(motherboard.getModelNumber(),
						motherboard.getProductName(),motherboard.isEccCompatibility(), motherboard.getWattage(),
						motherboard.getDimmSlotType().getSlotTypeId(),motherboard.getMemoryType().getMemoryTypeId(),
						motherboard.getComponent().getRating(), motherboard.getComponent().getPrice(),
						motherboardAndPciSlot.getId(), motherboardAndPciSlot.getQuantity(),
						motherboardAndPciSlot.getSlotType(), motherboard.getCpuSocketType().getSocket());
				motherboardJsonList.add(motherboardJson);
			}
		}
		return motherboardJsonList;
	}

	public Optional<Motherboard> getMotherBoard(Integer motherboardId)
			throws ResourceNotFoundException{
		Optional<Motherboard> motherboard = motherboardRepository.findByModelNumber(motherboardId);
		return motherboard;
	}

	public MotherboardJson updateMotherboard(Motherboard motherboard, MotherboardJson updatedMotherBoard) {
		motherboard.setProductName(updatedMotherBoard.getProductName());
		motherboard.setWattage(updatedMotherBoard.getWattage());
		motherboard.setEccCompatibility(updatedMotherBoard.isEccCompatibility());
		motherboard.setModelNumber(updatedMotherBoard.getModelNumber());

		Optional<DimmSlotType> dimmSlotType = dimmSlotTypeRepository.findBySlotTypeId(updatedMotherBoard.getDimmSlotTypeId());
		Optional<MemoryType> memoryType = memoryTypeRepository.findByMemoryTypeId(updatedMotherBoard.getMemoryTypeId());

		Optional<PciExpressSlotType> pciExpressSlotType = pCIExpressSlotTypeRepository.findByGeneration(updatedMotherBoard.getPciGeneration());

		motherboard.setDimmSlotType(dimmSlotType.get());

		motherboard.setMemoryType(memoryType.get());


		Set<MotherboardAndPciSlot> motherboardAndPciSlotSet  = motherboard.getMotherboardPCIslots();
		for (MotherboardAndPciSlot motherboardAndPciSlot: motherboardAndPciSlotSet) {
			motherboardAndPciSlot.setSlotType(pciExpressSlotType.get());
			motherboardAndPciSlot.setQuantity(updatedMotherBoard.getQuantity());
			updatedMotherBoard.setId(motherboardAndPciSlot.getId());
		}

		motherboard.setMotherboardPCIslots(motherboardAndPciSlotSet);

		motherboard.getComponent().setRating(updatedMotherBoard.getRating());
		motherboard.getComponent().setPrice(updatedMotherBoard.getPrice());
		motherboardRepository.save(motherboard);

		return updatedMotherBoard;
	}

	public void deleteMotherboard(Motherboard motherboard) {
		Integer compId = motherboard.getComponent().getComponentId();
		motherboardRepository.delete(motherboard);
		Optional<Component> component = componentRepository.findById(compId);
		componentRepository.delete(component.get());
	}

	public Optional<RamKit> getRam(Integer ramId)
			throws ResourceNotFoundException{
		Optional<RamKit> ram = ramKitRepository.findByModelNumber(ramId);
		return ram;
	}

	public RamJson updateRam(RamKit ramkit, RamJson updatedRamkit) {
		ramkit.setModelName(updatedRamkit.getModelName());
		ramkit.setModuleAmount(updatedRamkit.getModuleAmount());
		ramkit.setNumberOfDims(updatedRamkit.getNumberOfDims());
		ramkit.setModelNumber(updatedRamkit.getModelNumber());
		ramkit.setFrequency(updatedRamkit.getFrequency());
		ramkit.setEcc(updatedRamkit.isEcc());
		ramkit.setWattage(updatedRamkit.getWattage());

		Optional<CpuManufacturer> cpuManufacturer = cpuManufacturerRepository.findById(updatedRamkit.getManufacturerName());
		Optional<DimmSlotType> dimmSlotType = dimmSlotTypeRepository.findBySlotName(updatedRamkit.getSlotName());
		Optional<MemoryType> memoryType = memoryTypeRepository.findByRamGeneration(updatedRamkit.getRamGeneration());

		ramkit.setCompatibleCpu(cpuManufacturer.get());
		ramkit.setDimmSlotType(dimmSlotType.get());
		ramkit.setMemoryType(memoryType.get());

		ramkit.getComponent().setRating(updatedRamkit.getRating());
		ramkit.getComponent().setPrice(updatedRamkit.getPrice());

		ramKitRepository.save(ramkit);
		updatedRamkit.setId(ramkit.getId());
		return updatedRamkit;
	}

	public void deleteRam(RamKit ramkit) {
		Integer compId = ramkit.getComponent().getComponentId();
		ramKitRepository.delete(ramkit);
		Optional<Component> component = componentRepository.findById(compId);
		componentRepository.delete(component.get());
	}

	public RamJson createRam(RamJson ramJson){
		String modelName = ramJson.getModelName();
		int moduleAmount = ramJson.getModuleAmount();
		int numberOfDims = ramJson.getNumberOfDims();
		int modelNumber = ramJson.getModelNumber();

		Optional<CpuManufacturer> cpuManufacturer = cpuManufacturerRepository.findById(ramJson.getManufacturerName());

		int frequency = ramJson.getFrequency();
		boolean ecc = ramJson.isEcc();
		float wattage = ramJson.getWattage();
		Optional<DimmSlotType> dimmSlotType = dimmSlotTypeRepository.findBySlotName(ramJson.getSlotName());
		Component component = createComponent(ramJson.getRating(), ramJson.getPrice());
		Optional<MemoryType> memoryType = memoryTypeRepository.findByRamGeneration(ramJson.getRamGeneration());

		RamKit ramKit = ramKitRepository.save(new
				RamKit(modelName, moduleAmount, numberOfDims, modelNumber, cpuManufacturer.get(),
				frequency, ecc, wattage, dimmSlotType.get(), component, memoryType.get()));
		ramJson.setId(ramKit.getId());
		return ramJson;
	}

	public List<RamJson> getAllRams() {
		List<RamKit> ramList = ramKitRepository.findAll();
		return createRamResponse(ramList);

	}

	private List<RamJson> createRamResponse(List<RamKit> ramList) {
		List<RamJson> ramJsons = new ArrayList<>();
		for (RamKit ramKit :ramList) {
			ramJsons.add(new RamJson(ramKit.getId(), ramKit.getModelName(), ramKit.getModuleAmount(),
					ramKit.getNumberOfDims(), ramKit.getModelNumber(), ramKit.getCompatibleCpu().getManufacturerName(),
					ramKit.getFrequency(), ramKit.isEcc(), ramKit.getWattage(), ramKit.getComponent().getRating(),
					ramKit.getComponent().getPrice(), ramKit.getMemoryType().getRamGeneration(),
					ramKit.getDimmSlotType().getSlotName()));
		}
		return ramJsons;

	}

	public Optional<CPU> getCPU(Integer modelNumber)
			throws ResourceNotFoundException{
		Optional<CPU> cpu = cpuRepository.findByModelNumber(modelNumber);
		return cpu;
	}

	public CPUJson updateCPU(CPU cpu, CPUJson updatedCPU) {
		cpu.setModelNumber(updatedCPU.getModelNumber());
		cpu.setModelName(updatedCPU.getModelName());
		cpu.setNumberOfCores(updatedCPU.getNumberOfCores());
		cpu.setArchitecture(updatedCPU.getArchitecture());
		cpu.setEccCompatibility(updatedCPU.getEccCompatibility());
		cpu.setFrequency(updatedCPU.getFrequency());
		cpu.setTDP(updatedCPU.getTDP());
		cpu.setWattage(updatedCPU.getWattage());
		cpu.getComponent().setRating(updatedCPU.getRating());
		cpu.getComponent().setPrice(updatedCPU.getPrice());
		cpu.setClock(updatedCPU.getClock());

		Optional<CpuManufacturer> cpuManufacturer = cpuManufacturerRepository.findById(updatedCPU.getManufacturerName());

		Optional<CpuSocketType> cpuSocketType = cpuSocketRepository.findBySocket(updatedCPU.getSocket());
		Optional<MemoryType> memoryType = memoryTypeRepository.findByRamGeneration(updatedCPU.getRamGeneration());

		cpu.setCpuManufacturer(cpuManufacturer.get());
		cpu.setMemoryType(memoryType.get());
		cpu.setCpuSocketType(cpuSocketType.get());

		cpuRepository.save(cpu);
		updatedCPU.setId(cpu.getId());

		return updatedCPU;


	}

	public void deleteCPU(CPU cpu) {
		Integer compId = cpu.getComponent().getComponentId();
		cpuRepository.delete(cpu);
		Optional<Component> component = componentRepository.findById(compId);
		componentRepository.delete(component.get());
	}

	public CPUJson createCPU(CPUJson cpuJson){
		Integer modelNumber = cpuJson.getModelNumber();
		String modelName = cpuJson.getModelName();
		short numberOfCores = cpuJson.getNumberOfCores();
		String architecture = cpuJson.getArchitecture();
		Boolean eccCompatibility = cpuJson.getEccCompatibility();
		int frequency = cpuJson.getFrequency();
		int tdp = cpuJson.getTDP();
		int wattage = cpuJson.getWattage();

		Component component = createComponent(cpuJson.getRating(), cpuJson.getPrice());
		Optional<CpuManufacturer> cpuManufacturer = cpuManufacturerRepository.findById(cpuJson.getManufacturerName());
		Optional<CpuSocketType> cpuSocketType = cpuSocketRepository.findBySocket(cpuJson.getSocket());
		Optional<MemoryType> memoryType = memoryTypeRepository.findByRamGeneration(cpuJson.getRamGeneration());

		int clock = cpuJson.getClock();
		CPU cpu = cpuRepository.save(new
				CPU(modelNumber, modelName, numberOfCores, architecture, eccCompatibility, frequency, tdp,
				wattage, component, cpuManufacturer.get(), cpuSocketType.get(), clock, memoryType.get()));
		cpuJson.setId(cpu.getId());
		return cpuJson;
	}

	public List<CPUJson> getAllCpus() {
		List<CPU> cpulist = cpuRepository.findAll();
		return createCpuResponse(cpulist);
		
		//return createMbResponse(motherboardList);
	}

	private List<CPUJson> createCpuResponse(List<CPU> cpulist) {
		List<CPUJson> cpuJsons = new ArrayList<>();
		for (CPU cpu :cpulist) {
			cpuJsons.add(new CPUJson(cpu.getId(), cpu.getModelNumber(), cpu.getModelName(), cpu.getNumberOfCores(), cpu.getArchitecture(),
			cpu.getEccCompatibility(), cpu.getFrequency(), cpu.getTDP(), cpu.getWattage(), cpu.getComponent().getRating(),
					cpu.getComponent().getPrice(),cpu.getClock(), cpu.getCpuManufacturer().getManufacturerName(),
					cpu.getCpuSocketType().getSocket(), cpu.getMemoryType().getRamGeneration()));
		}
		return cpuJsons;
	}


	public Optional<GraphicsCard> getGraphics(String modelNumber)
			throws ResourceNotFoundException{
		Optional<GraphicsCard> graphicsCard = graphicsCardRepository.findByModelNumber(modelNumber);
		return graphicsCard;
	}

	public GraphicCardJson updateGraphics(GraphicsCard graphicsCard, GraphicCardJson updatedGraphicsCard) {
		//--
		graphicsCard.setModelNumber(updatedGraphicsCard.getModelNumber());
		graphicsCard.setModelName(updatedGraphicsCard.getModelName());

		graphicsCard.setWattage(updatedGraphicsCard.getWattage());
		graphicsCard.setTDP(updatedGraphicsCard.getTDP());
		graphicsCard.setTfs(updatedGraphicsCard.getTfs());
		graphicsCard.setClockSpeed(updatedGraphicsCard.getClockSpeed());
		graphicsCard.setProcess(updatedGraphicsCard.getProcess());

		Optional<GpuManufacturer> gpuManufacturer = gpuManufacturerRepository.findById(updatedGraphicsCard.getManufacturerName());
		Optional<PciExpressSlotType> pciExpressSlotType = pCIExpressSlotTypeRepository.findByGeneration(updatedGraphicsCard.getGeneration());

		graphicsCard.setGpuManufacturer(gpuManufacturer.get());
		graphicsCard.setPciExpressSlotType(pciExpressSlotType.get());

		graphicsCard.getComponent().setRating(updatedGraphicsCard.getRating());
		graphicsCard.getComponent().setPrice(updatedGraphicsCard.getPrice());

		graphicsCardRepository.save(graphicsCard);
		updatedGraphicsCard.setId(graphicsCard.getId());
		return updatedGraphicsCard;

	}

	public void deleteGraphicsCard(GraphicsCard graphicsCard) {
		Integer compId = graphicsCard.getComponent().getComponentId();
		graphicsCardRepository.delete(graphicsCard);
		Optional<Component> component = componentRepository.findById(compId);
		componentRepository.delete(component.get());
	}

	public GraphicCardJson createGraphics(GraphicCardJson graphicCardJson){

		String modelNumber = graphicCardJson.getModelNumber();
		String modelName = graphicCardJson.getModelName();
		Optional<GpuManufacturer> gpuManufacturer = gpuManufacturerRepository.findById(graphicCardJson.getManufacturerName());

		Optional<PciExpressSlotType> pciExpressSlotType = pCIExpressSlotTypeRepository.findByGeneration(graphicCardJson.getGeneration());

		int wattage = graphicCardJson.getWattage();
		int process = graphicCardJson.getProcess();
		int tdp = graphicCardJson.getTDP();
		float tfs = graphicCardJson.getTfs();
		float clockSpeed = graphicCardJson.getClockSpeed();

		Component component = createComponent(graphicCardJson.getRating(), graphicCardJson.getPrice());

		GraphicsCard graphicsCard = graphicsCardRepository.save(new
				GraphicsCard(modelNumber, modelName,process, gpuManufacturer.get(),
				pciExpressSlotType.get(), tdp, wattage, tfs, clockSpeed, component));
		graphicCardJson.setId(graphicsCard.getId());
		return graphicCardJson;
	}

	public List<GraphicCardJson> getAllGraphicsCards() {
		List<GraphicsCard> graphicsCardsList = graphicsCardRepository.findAll();
	//	return graphicsCardsList;
		return createGraphicsCard(graphicsCardsList);
	}


	private List<GraphicCardJson> createGraphicsCard(List<GraphicsCard> graphicsCardsList) {
		List<GraphicCardJson> graphicCardJsons = new ArrayList<>();
		for (GraphicsCard graphicsCard :graphicsCardsList) {
			graphicCardJsons.add(new GraphicCardJson(graphicsCard.getId(), graphicsCard.getModelNumber(), graphicsCard.getModelName(),
					graphicsCard.getProcess(), graphicsCard.getTDP(), graphicsCard.getWattage(),
					graphicsCard.getTfs(),graphicsCard.getClockSpeed(), graphicsCard.getComponent().getRating(),
					graphicsCard.getComponent().getPrice(), graphicsCard.getGpuManufacturer().getManufacturerName(),
					graphicsCard.getPciExpressSlotType().getGeneration()));
		}
		return graphicCardJsons;
	}

	public UserPCConfig getPcConfigsById(String username) {
		List<PCConfiguration> pcConfigurationList = pcConfigurationRepository.findByUsernameIn(username);
		pcConfigurationList = (pcConfigurationList == null || pcConfigurationList.size()==0) ?
				new ArrayList<PCConfiguration>():pcConfigurationList;
		return createPcConfigResponse(pcConfigurationList);
	}

	private UserPCConfig createPcConfigResponse(List<PCConfiguration> pcConfigurationList) {
		List<CPU> cpus = cpuRepository.findAll();
		List<RamKit> rams = ramKitRepository.findAll();
		List<Motherboard> motherboards = motherboardRepository.findAll();
		List<GraphicsCard> graphicsCards = graphicsCardRepository.findAll();
		UserPCConfig userPCConfig = new UserPCConfig(cpus, rams, motherboards, graphicsCards, pcConfigurationList, true, 0);
		return userPCConfig;
	}

	public UserPCConfig createUserPCConfig(String username, PCConfiguration pCConfiguration) {
		Optional<User> user = userRepository.findByUsername(username);
		pCConfiguration.setUser(user.get());
		UserPCConfig userPCConfig = getPcConfigsById(pCConfiguration.getUser().getUsername());

		List<Motherboard> motherboard = motherboardRepository.findByProductName(pCConfiguration.getMotherboard());
		List<CPU> cpu = cpuRepository.findByModelName(pCConfiguration.getCpu());
		Integer mbModelNumber = motherboard.get(0).getModelNumber();
		Integer cpuModelNumber = cpu.get(0).getModelNumber();
		String ramModelName = pCConfiguration.getRam();
		String graphicsCardModelName = pCConfiguration.getGraphicsCard();
		boolean check1 = ramKitRepository.checkMotherboardRam(mbModelNumber, ramModelName);
		boolean check2 = ramKitRepository.checkMotherboardGPU(mbModelNumber,graphicsCardModelName);
		boolean check3 = ramKitRepository.checkMotherboardCPU(mbModelNumber,cpuModelNumber);
		boolean check4 = ramKitRepository.checkCpuRam(cpuModelNumber,ramModelName);
		if (check1 && check3 && check4 ) {
			pcConfigurationRepository.save(pCConfiguration);
			userPCConfig.setIsCorrectConfig(true);
			userPCConfig.setNewPCConfigId(pCConfiguration.getId());
		} else {
			userPCConfig.setIsCorrectConfig(false);
		}
		return userPCConfig;
	}

	public Optional<PCConfiguration> getPcConfigsByConfigId(Integer id) {
		Optional<PCConfiguration> pcConfiguration = pcConfigurationRepository.findById(id);

		return pcConfiguration;
	}

	public void deletePcConfig(PCConfiguration pCConfiguration) {
		pcConfigurationRepository.delete(pCConfiguration);
	}

	public UserPCConfig updatePCConfig(PCConfiguration pCConfiguration, PCConfiguration pcConfigurationDetails) {
		pCConfiguration.setRam(pcConfigurationDetails.getRam());
		pCConfiguration.setCpu(pcConfigurationDetails.getCpu());
		pCConfiguration.setGraphicsCard(pcConfigurationDetails.getGraphicsCard());
		pCConfiguration.setMotherboard(pcConfigurationDetails.getMotherboard());
		pCConfiguration = pcConfigurationRepository.save(pCConfiguration);

		UserPCConfig userPCConfig = getPcConfigsById(pCConfiguration.getUser().getUsername());
		userPCConfig.setIsCorrectConfig(true);
		userPCConfig.setNewPCConfigId(pCConfiguration.getId());
		return userPCConfig;
	}

	public List<CpuSocketType> getCpuSocketTypes() {
		return cpuSocketRepository.findAll();
	}

	public List<MemoryType> getAllMemoryTypes() {
		return memoryTypeRepository.findAll();
	}

	public List<PciExpressSlotType> getAllPCIExpressSlotTypes() {
		return pCIExpressSlotTypeRepository.findAll();
	}

	public List<DimmSlotType> getAllDimSlotTypes() {
		return dimmSlotTypeRepository.findAll();
	}

	public List<GpuManufacturer> getAllGpuManufacturers() {
		return gpuManufacturerRepository.findAll();
	}

	public List<CpuManufacturer> getAllCPUManufactures() {
		return cpuManufacturerRepository.findAll();
	}
}