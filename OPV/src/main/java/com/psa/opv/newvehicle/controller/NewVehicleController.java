package com.psa.opv.newvehicle.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping(value = "/allnewvehicles")
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
	@PutMapping(value = "/update/{vehicleid}")
	public ResponseEntity<NewVehicleDTO> updateNewVehicle(
			@PathVariable(name = "vehicleid", required = true) String vehicleId,
			@RequestBody(required = true) NewVehicleDTO newVehicleDto) {
		Optional<NewVehicleDTO> updateNewVehicleId = iNewVehicleService.updateNewVehicleId(newVehicleDto, vehicleId);
		return new ResponseEntity<NewVehicleDTO>(
				updateNewVehicleId.orElseThrow(
						() -> new NewVehiclesNotFoundException(NewVehicleConstants.NOT_FOUND + ":" + vehicleId)),
				HttpStatus.OK);
	}

}
