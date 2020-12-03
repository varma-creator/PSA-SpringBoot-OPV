package com.psa.opv.newvehicle.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psa.opv.newvehicle.constants.NewVehicleConstants;
import com.psa.opv.newvehicle.dto.NewVehicleDTO;
import com.psa.opv.newvehicle.exception.NewVehicleAlreardyFoundException;
import com.psa.opv.newvehicle.exception.NewVehiclesNotFoundException;
import com.psa.opv.newvehicle.service.INewVehicleService;

/**
 * The class represents NewVehicle Controller and handles the NewVehicle HTTP
 * request
 * 
 * @author Varma
 *
 */
@RestController
@RequestMapping(value = "/api")
@Validated
public class NewVehicleController {

	@Autowired
	private INewVehicleService iNewVehicleService;

	/**
	 * @param newVehicleDto
	 * @return ResponseEntity<NewVehicleDTO>
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<NewVehicleDTO> addNewVehicle(
			@Valid @RequestBody(required = true) NewVehicleDTO newVehicleDto) {
		Optional<NewVehicleDTO> newVehDto = iNewVehicleService.addNewVehicle(newVehicleDto);
		return new ResponseEntity<NewVehicleDTO>(
				newVehDto.orElseThrow(
						() -> new NewVehicleAlreardyFoundException(NewVehicleConstants.NEW_VEHICLE_ALREADT_EXIST + "_"
								+ newVehicleDto.getVehicleType() + "_" + newVehicleDto.getVehicleName())),
				HttpStatus.CREATED);
	}

	/**
	 * @return ResponseEntity
	 */
	@GetMapping(value = "get/allnewvehicles")
	public ResponseEntity<List<NewVehicleDTO>> getAllNewVehicles() {
		Optional<List<NewVehicleDTO>> allNewVehicles = iNewVehicleService.getAllNewVehicles();
		return new ResponseEntity<List<NewVehicleDTO>>(
				allNewVehicles.orElseThrow(
						() -> new NewVehiclesNotFoundException(NewVehicleConstants.NEW_VEHICLE_ALL_NOT_FOUND)),
				HttpStatus.OK);
	}

	/**
	 * @param vehicleId
	 * @param newVehicleDto
	 * @return
	 */
	@PutMapping(value = "/update/{vehicleId}")
	public ResponseEntity<NewVehicleDTO> updateNewVehicle(
			@PathVariable(name = "vehicleId", required = true) @NotBlank(message = "vehicleId must not be empty") String vehicleId,
			@RequestBody(required = true) NewVehicleDTO newVehicleDto) {
		Optional<NewVehicleDTO> updateNewVehicleId = iNewVehicleService.updateNewVehicleId(newVehicleDto, vehicleId);
		return new ResponseEntity<NewVehicleDTO>(
				updateNewVehicleId.orElseThrow(
						() -> new NewVehiclesNotFoundException(NewVehicleConstants.NOT_FOUND + ":" + vehicleId)),
				HttpStatus.OK);
	}

	/**
	 * @param vehicleID
	 * @return NewVehicleDTO
	 */
	@GetMapping(value = "/get/{vehicleId}")
	public ResponseEntity<NewVehicleDTO> getVehicleById(
			@PathVariable(name = "vehicleId", required = true) @NotBlank(message = "vehicleId must not be empty") String vehicleId) {
		Optional<NewVehicleDTO> newVehicleID = iNewVehicleService.getNewVehicleID(vehicleId);
		return new ResponseEntity<NewVehicleDTO>(
				newVehicleID.orElseThrow(
						() -> new NewVehiclesNotFoundException(NewVehicleConstants.NOT_FOUND + ":" + vehicleId)),
				HttpStatus.OK);
	}

	/**
	 * @param vehicleId
	 * @return NewVehicleDTO
	 */
	@DeleteMapping(value = "/delete/{vehicleId}")
	public ResponseEntity<NewVehicleDTO> deleteVehicleID(
			@PathVariable(name = "vehicleId", required = true) @NotBlank(message = "vehicleId must not be empty") String vehicleId) {
		Optional<NewVehicleDTO> deleteByVehicleID = iNewVehicleService.deleteByVehicleID(vehicleId);
		return new ResponseEntity<NewVehicleDTO>(
				deleteByVehicleID.orElseThrow(
						() -> new NewVehiclesNotFoundException(NewVehicleConstants.NOT_FOUND + ":" + vehicleId)),
				HttpStatus.OK);
	}
	
	/**
	 * @param vehiclType
	 * @return List<NewVehicleDTO>
	 */
	@DeleteMapping(value = "/alldelete/{vehiclType}")
	public ResponseEntity<List<NewVehicleDTO>> deleteVehicleType(
			@PathVariable(name = "vehiclType", required = true) @NotBlank(message = "vehicletype must not be empty") @Size(min = 2 ,message="At least one value needs to be specified") String vehiclType) {
		Optional<List<NewVehicleDTO>> deletedListVehicleType = iNewVehicleService.removeByVehicleType(vehiclType);
		return new ResponseEntity<List<NewVehicleDTO>>(
				deletedListVehicleType.orElseThrow(
						() -> new NewVehiclesNotFoundException(NewVehicleConstants.NOT_FOUND + ":" + vehiclType)),
				HttpStatus.OK);
	}

	/**
	 * @param vehicleType
	 * @param vehicleColour
	 * @return
	 */
	@GetMapping(value = "/get/filter")
	public ResponseEntity<List<NewVehicleDTO>> getVehicleTypeAndColour(@RequestParam(name = "type", required = true)
	@NotBlank(message = "vehicletype must not be empty") @Size(min = 2) String vehicleType,
			@RequestParam(name = "colour") @NotBlank(message = "vehicleColour must not be empty") @Size(min = 3) String vehicleColour) {
		Optional<List<NewVehicleDTO>> byVehicleTypeAndVehicleColour = iNewVehicleService
				.getByVehicleTypeAndVehicleColour(vehicleType, vehicleColour);
		return new ResponseEntity<List<NewVehicleDTO>>(byVehicleTypeAndVehicleColour
				.orElseThrow(() -> new NewVehiclesNotFoundException(NewVehicleConstants.NOT_FOUND + ":" + vehicleType
						+ NewVehicleConstants.CONCAT_OPERATOR + vehicleColour)),
				HttpStatus.OK);
	}
}
