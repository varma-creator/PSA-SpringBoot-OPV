package com.psa.opv.newvehicle.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.psa.opv.newvehicle.dto.NewVehicleTypeDTO;
import com.psa.opv.newvehicle.exception.NewVehicleAlreardyFoundException;
import com.psa.opv.newvehicle.exception.NewVehiclesNotFoundException;
import com.psa.opv.newvehicle.service.INewVehicleService;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class NewVehicleController {

	@Autowired
	private INewVehicleService iNewVehicleService;

	/**
	 * @param newVehicleDto
	 * @return ResponseEntity<NewVehicleDTO>
	 * consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE defaulty
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<NewVehicleDTO> addNewVehicle(
			@Valid @RequestBody(required = true) NewVehicleDTO newVehicleDto) {
		log.info("addNewVehicle()->{}", "Vehicle Data:" + newVehicleDto);
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
		log.info("getAllNewVehicles()->{}");
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
		log.info("updateNewVehicles()->{}", "VehicleId:" + vehicleId);
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
		log.info("getVehicleId()->{}", "vehicleId:" + vehicleId);
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
	public ResponseEntity<NewVehicleDTO> deleteVehicleId(
			@PathVariable(name = "vehicleId", required = true) @NotBlank(message = "vehicleId must not be empty") String vehicleId) {
		log.info("deleteVehicleId()->{}", "vehicleId:" + vehicleId);
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
			@PathVariable(name = "vehiclType", required = true) @NotBlank(message = "vehicletype must not be empty") @Size(min = 2, message = "At least one value needs to be specified") String vehiclType) {
		log.info("getVehicletype()->{}", "vehicleType:" + vehiclType);
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
	public ResponseEntity<List<NewVehicleDTO>> getVehicleTypeAndColour(
			@RequestParam(name = "type", required = true) @NotBlank(message = "vehicletype must not be empty") @Size(min = 2) String vehicleType,
			@RequestParam(name = "colour") @NotBlank(message = "vehicleColour must not be empty") @Size(min = 3) String vehicleColour) {
		log.info("getVehicleTypeAndColour()->{}", "vehicletype=" + vehicleType, "vehicleColour=" + vehicleColour);
		Optional<List<NewVehicleDTO>> byVehicleTypeAndVehicleColour = iNewVehicleService
				.getByVehicleTypeAndVehicleColour(vehicleType, vehicleColour);
		return new ResponseEntity<List<NewVehicleDTO>>(byVehicleTypeAndVehicleColour
				.orElseThrow(() -> new NewVehiclesNotFoundException(NewVehicleConstants.NOT_FOUND + ":" + vehicleType
						+ NewVehicleConstants.CONCAT_OPERATOR + vehicleColour)),
				HttpStatus.OK);
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GetMapping(value = "/get/date")
	public ResponseEntity<List<NewVehicleDTO>> getNewVehicleBetweenManfDates(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		Optional<List<NewVehicleDTO>> findByVehicleManfDateBetween = iNewVehicleService
				.findByVehicleManfDateBetween(startDate, endDate);
		return new ResponseEntity<List<NewVehicleDTO>>(findByVehicleManfDateBetween.get(), HttpStatus.OK);

	}

	@GetMapping(value = "/get/typevehicles")
	public ResponseEntity<List<NewVehicleTypeDTO>> getNewVehicleType(
			@RequestParam(name = "Nvtype") String newVehicletype) {
		List<NewVehicleTypeDTO> findByVehicleType = iNewVehicleService.findByVehicleType(newVehicletype);
		return new ResponseEntity<List<NewVehicleTypeDTO>>(findByVehicleType, HttpStatus.OK);
	}
}
