package com.pcPartMaker.controller;

import java.util.*;

import javax.validation.Valid;

import com.pcPartMaker.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.pcPartMaker.exception.ResourceNotFoundException;
import com.pcPartMaker.service.PCPartService;
import com.pcPartMaker.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class ComponentController {

	@Autowired
	private PCPartService pCPartService;

	/**
	 * Get all motherboards.
	 *
	 * @return the list
	 */
	@GetMapping(value = "/motherboards", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<List<MotherboardJson>> getMotherBoards() {
		try {
			List<MotherboardJson> motherboards = pCPartService.getAllMotherboards();

			if(null == motherboards || motherboards.size()==0) {
				motherboards = new ArrayList<>();
			}
			return new ResponseEntity<>(motherboards, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets motherboard by id.
	 *
	 * @param motherboardId the motherboard id
	 * @return the motherboard by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/motherboards/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Motherboard> getMotherBoardById(@PathVariable(value = "id") Integer motherboardId)
			throws ResourceNotFoundException {
		Motherboard motherboard = pCPartService.getMotherBoard(motherboardId).orElseThrow(() -> new ResourceNotFoundException("Motherboard not found on :: " + motherboardId));
		return new ResponseEntity<>(motherboard, HttpStatus.OK);
	}

	/**
	 * Create motherboard motherboard.
	 *
	 * @param motherboardJson the motherboardJson
	 * @return the motherboard
	 */
	@PostMapping("/motherboards")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MotherboardJson> createMotherboard(@Valid @RequestBody MotherboardJson motherboardJson) {
		try {
			if(null == motherboardJson) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			motherboardJson = pCPartService.createMotherboard(motherboardJson);
			return new ResponseEntity<>(motherboardJson, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update user response entity.
	 *
	 * @param modelNumber the user id
	 * @param motherboardJsonDetails the user details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/motherboards/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MotherboardJson> updateMotherboard(
			@PathVariable(value = "id") Integer modelNumber, @Valid @RequestBody  MotherboardJson motherboardJsonDetails)
					throws ResourceNotFoundException {
		Motherboard motherboard =
				pCPartService.getMotherBoard(modelNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Motherboard not found on :: " + modelNumber));
		motherboardJsonDetails = pCPartService.updateMotherboard(motherboard, motherboardJsonDetails);
		return new ResponseEntity<>(motherboardJsonDetails, HttpStatus.OK);
	}

	/**
	 * Delete user map.
	 *
	 * @param modelNumber the user id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/motherboards/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteMotherboard
		(@PathVariable(value = "id") Integer modelNumber) throws Exception {
		Motherboard motherboard = pCPartService.getMotherBoard(modelNumber).orElseThrow(()
				-> new ResourceNotFoundException("Motherboard not found on :: " + modelNumber));

		Map<String, Boolean> response = new HashMap<>();
		pCPartService.deleteMotherboard(motherboard);
		response.put("deleted", Boolean.TRUE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Get all rams.
	 *
	 * @return the list
	 */
	@GetMapping(value = "/rams", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<List<RamJson>> getRams() {
		try {
			List<RamJson> ramJsons = pCPartService.getAllRams();

			if(null == ramJsons || ramJsons.size()==0) {
				ramJsons = new ArrayList<>();
			}
			return new ResponseEntity<>(ramJsons, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets ram by id.
	 *
	 * @param ramId the ram id
	 * @return the ram by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/rams/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<RamKit> getRamById(@PathVariable(value = "id") Integer ramId)
			throws ResourceNotFoundException {
		RamKit ramKit = pCPartService.getRam(ramId).orElseThrow(() -> new ResourceNotFoundException("Ram not found on :: " + ramId));
		return new ResponseEntity<>(ramKit, HttpStatus.OK);
	}

	/**
	 * Create ram ram.
	 *
	 * @param ramJson the ram
	 * @return the motherboard
	 */
	@PostMapping("/rams")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<RamJson>createRam(@Valid @RequestBody RamJson ramJson) {
		try {
			if(null == ramJson) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ramJson = pCPartService.createRam(ramJson);
			return new ResponseEntity<>(ramJson, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update user response entity.
	 *
	 * @param modelNumber the ram id
	 * @param ramJsonDetails the user details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/rams/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<RamJson> updateRam(
			@PathVariable(value = "id") Integer modelNumber, @Valid @RequestBody RamJson ramJsonDetails)
			throws ResourceNotFoundException {
		RamKit ramKit =
				pCPartService.getRam(modelNumber)
						.orElseThrow(() -> new ResourceNotFoundException("Ram not found on :: " + modelNumber));
		RamJson ramJson = pCPartService.updateRam(ramKit, ramJsonDetails);
		return new ResponseEntity<>(ramJson, HttpStatus.OK);
	}

	/**
	 * Delete user map.
	 *
	 * @param modelNumber the ram id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/rams/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteRam
	(@PathVariable(value = "id") Integer modelNumber) throws Exception {
		RamKit ramKit = pCPartService.getRam(modelNumber).orElseThrow(()
				-> new ResourceNotFoundException("Ram not found on :: " + modelNumber));

		Map<String, Boolean> response = new HashMap<>();
		pCPartService.deleteRam(ramKit);
		response.put("deleted", Boolean.TRUE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Get all cpus.
	 *
	 * @return the list
	 */
	@GetMapping(value = "/cpus", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<List<CPUJson>> getCPUs() {
		try {
			List<CPUJson> cpusJson = pCPartService.getAllCpus();

			if(null == cpusJson || cpusJson.size()==0) {
				cpusJson = new ArrayList<>();
			}
			return new ResponseEntity<>(cpusJson, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets cpu by id.
	 *
	 * @param modelNumber the cpu id
	 * @return the ram by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/cpus/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CPU> getCPUById(@PathVariable(value = "id") Integer modelNumber)
			throws ResourceNotFoundException {
		CPU cPU = pCPartService.getCPU(modelNumber).orElseThrow(() -> new ResourceNotFoundException("CPU not found on :: " + modelNumber));
		return new ResponseEntity<>(cPU, HttpStatus.OK);
	}

	/**
	 * Create cpu cpu.
	 *
	 * @param cpuJson the cpu
	 * @return the motherboard
	 */
	@PostMapping("/cpus")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CPUJson> createCPU(@Valid @RequestBody CPUJson cpuJson) {
		try {
			if(null == cpuJson) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			cpuJson = pCPartService.createCPU(cpuJson);
			return new ResponseEntity<>(cpuJson, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update user response entity.
	 *
	 * @param modelNumber the cpu id
	 * @param cpuDetails the user details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/cpus/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CPUJson> updateCPU(
			@PathVariable(value = "id") Integer modelNumber, @Valid @RequestBody CPUJson cpuDetails)
			throws ResourceNotFoundException {
		CPU cpu =
				pCPartService.getCPU(modelNumber)
						.orElseThrow(() -> new ResourceNotFoundException("CPU not found on :: " + modelNumber));
		CPUJson updatedCPU = pCPartService.updateCPU(cpu, cpuDetails);
		return new ResponseEntity<>(updatedCPU, HttpStatus.OK);
	}

	/**
	 * Delete cpu
	 *
	 * @param modelNumber the cpu id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/cpus/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteCPU
	(@PathVariable(value = "id") Integer modelNumber) throws Exception {
		CPU cpu = pCPartService.getCPU(modelNumber).orElseThrow(()
				-> new ResourceNotFoundException("CPU not found on :: " + modelNumber));

		Map<String, Boolean> response = new HashMap<>();
		pCPartService.deleteCPU(cpu);
		response.put("deleted", Boolean.TRUE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Get all graphics.
	 *
	 * @return the list
	 */
	@GetMapping(value = "/graphics", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<List<GraphicCardJson>> getGraphics() {
		try {
			List<GraphicCardJson> graphicCardJsons = graphicCardJsons = pCPartService.getAllGraphicsCards();


			if(null == graphicCardJsons || graphicCardJsons.size()==0) {
				graphicCardJsons = new ArrayList<>();
			}
			return new ResponseEntity<>(graphicCardJsons, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets graphics by id.
	 *
	 * @param modelNumber the graphics card id
	 * @return the graphics by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/graphics/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<GraphicsCard> getGraphicsById(@PathVariable(value = "id") String modelNumber)
			throws ResourceNotFoundException {
		GraphicsCard graphicsCard = pCPartService.getGraphics(modelNumber).orElseThrow(() -> new ResourceNotFoundException("Graphics not found on :: " + modelNumber));
		return new ResponseEntity<>(graphicsCard, HttpStatus.OK);
	}

	/**
	 * Create graphics graphics card.
	 *
	 * @param graphicCardJson the cpu
	 * @return the graphicsCard
	 */
	@PostMapping("/graphics")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<GraphicCardJson> createGraphics(@Valid @RequestBody GraphicCardJson graphicCardJson) {
		try {
			if(null == graphicCardJson) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			graphicCardJson = pCPartService.createGraphics(graphicCardJson);
			return new ResponseEntity<>(graphicCardJson, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update user response entity.
	 *
	 * @param modelNumber the cpu id
	 * @param graphicsCardDetails the user details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/graphics/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<GraphicCardJson> updateGraphics(
			@PathVariable(value = "id") String modelNumber, @Valid @RequestBody GraphicCardJson graphicsCardDetails)
			throws ResourceNotFoundException {
		GraphicsCard graphicsCard =
				pCPartService.getGraphics(modelNumber)
						.orElseThrow(() -> new ResourceNotFoundException("Graphics not found on :: " + modelNumber));
		GraphicCardJson updatedGraphics = pCPartService.updateGraphics(graphicsCard, graphicsCardDetails);
		return new ResponseEntity<>(updatedGraphics, HttpStatus.OK);
	}

	/**
	 * Delete cpu
	 *
	 * @param modelNumber the cpu id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/graphics/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteGraphics
	(@PathVariable(value = "id") String modelNumber) throws Exception {
		GraphicsCard graphicsCard = pCPartService.getGraphics(modelNumber).orElseThrow(()
				-> new ResourceNotFoundException("Graphics not found on :: " + modelNumber));

		Map<String, Boolean> response = new HashMap<>();
		pCPartService.deleteGraphicsCard(graphicsCard);
		response.put("deleted", Boolean.TRUE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Get all pc configs.
	 *
	 * @return the list
	 */
	@GetMapping(value = "/pcConfigs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<UserPCConfig> getPcConfigs(
			@PathVariable(value = "id") String username) {
		try {
			UserPCConfig userPCConfigs = pCPartService.getPcConfigsById(username);

//			if (null == userPCConfigs) {
//				userPCConfigs = new UserPCConfig();
//			}
			return new ResponseEntity<>(userPCConfigs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//
//		/**
//		 * Gets graphics by id.
//		 *
//		 * @param modelNumber the graphics card id
//		 * @return the graphics by id
//		 * @throws ResourceNotFoundException the resource not found exception
//		 */
	/*	@GetMapping("/graphics/{id}")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<GraphicsCard> getGraphicsById(@PathVariable(value = "id") String modelNumber)
			throws ResourceNotFoundException {
			GraphicsCard graphicsCard = pCPartService.getGraphics(modelNumber).orElseThrow(() -> new ResourceNotFoundException("Graphics not found on :: " + modelNumber));
			return new ResponseEntity<>(graphicsCard, HttpStatus.OK);
		}*/

		/**
		 * Create pc config.
		 *
		 * @param pcConfiguration the cpu
		 * @return the graphicsCard
		 */
		@PostMapping("/pcConfigs/{id}")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<UserPCConfig> createPCConfigs(@PathVariable(value = "id") String username,
															@Valid @RequestBody PCConfiguration pcConfiguration) {
			try {
				if(null == pcConfiguration) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				UserPCConfig userPCConfig = pCPartService.createUserPCConfig(username, pcConfiguration);
				return new ResponseEntity<>(userPCConfig, HttpStatus.OK);
			}
			catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		/**
		 * Update user response entity.
		 *
		 * @param configId the config id
		 * @param pcConfigurationDetails the pc config details
		 * @return the response entity
		 * @throws ResourceNotFoundException the resource not found exception
		 */
		@PutMapping("/pcConfigs/{id}")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<UserPCConfig>  updatePcConfigs(@PathVariable(value = "id") Integer configId,
															@Valid @RequestBody PCConfiguration pcConfigurationDetails)
			throws ResourceNotFoundException {
			PCConfiguration pCConfiguration =
					pCPartService.getPcConfigsByConfigId(configId)
							.orElseThrow(() -> new ResourceNotFoundException("PC configuration not found on :: " + configId));
			UserPCConfig userPCConfig  = pCPartService.updatePCConfig(pCConfiguration, pcConfigurationDetails);
			return new ResponseEntity<>(userPCConfig, HttpStatus.OK);
		}

		/**
		 * Delete cpu
		 *
		 * @param configId the config id
		 * @return the map
		 * @throws Exception the exception
		 */
		@DeleteMapping("/pcConfigs/{id}")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<Map<String, Boolean>> deletePcConfigs
		(@PathVariable(value = "id") Integer configId) throws Exception {
			PCConfiguration pCConfiguration = pCPartService.getPcConfigsByConfigId(configId).orElseThrow(()
					-> new ResourceNotFoundException("pCConfiguration not found on :: " + configId));

			Map<String, Boolean> response = new HashMap<>();
			pCPartService.deletePcConfig(pCConfiguration);
			response.put("deleted", Boolean.TRUE);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

	@GetMapping(value = "/cpusockets", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<List<CpuSocketType>> getAllCpuSockets() {
		try {
			List<CpuSocketType> cpuSocketTypes = pCPartService.getCpuSocketTypes();

			if(null == cpuSocketTypes || cpuSocketTypes.size()==0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cpuSocketTypes, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/memorytypes", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<List<MemoryType>> getAllMemoryTypes() {
		try {
			List<MemoryType> memoryTypes = pCPartService.getAllMemoryTypes();

			if(null == memoryTypes || memoryTypes.size()==0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(memoryTypes, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/dimslots", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<List<DimmSlotType>> getAllDimSlotTypes() {
		try {
			List<DimmSlotType> dimmSlotTypes = pCPartService.getAllDimSlotTypes();

			if(null == dimmSlotTypes || dimmSlotTypes.size()==0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(dimmSlotTypes, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/pciexpressslottypes", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<List<PciExpressSlotType>> getAllPCIExpressSlotTypes() {
		try {
			List<PciExpressSlotType> pciExpressSlotTypes = pCPartService.getAllPCIExpressSlotTypes();

			if(null == pciExpressSlotTypes || pciExpressSlotTypes.size()==0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(pciExpressSlotTypes, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/cpumanufacturers", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<List<CpuManufacturer>> getAllCPUManufactures() {
		try {
			List<CpuManufacturer> cpuManufacturers = pCPartService.getAllCPUManufactures();

			if(null == cpuManufacturers || cpuManufacturers.size()==0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cpuManufacturers, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/gpumanufacturers", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<List<GpuManufacturer>> getAllGpuManufacturers() {
		try {
			List<GpuManufacturer> gpuManufacturers = pCPartService.getAllGpuManufacturers();

			if(null == gpuManufacturers || gpuManufacturers.size()==0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(gpuManufacturers, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
