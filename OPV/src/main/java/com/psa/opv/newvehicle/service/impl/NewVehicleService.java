package com.psa.opv.newvehicle.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.opv.newvehicle.dto.NewVehicleDTO;
import com.psa.opv.newvehicle.entity.NewVehicle;
import com.psa.opv.newvehicle.repository.NewVehicleRepository;
import com.psa.opv.newvehicle.service.INewVehicleService;
import com.psa.opv.newvehicle.utility.UtilityObjectConversion;

/**
 * @author Varma
 *
 */

@Service
public class NewVehicleService implements INewVehicleService {

	@Autowired
	private NewVehicleRepository newVehicleRepository;

	@Autowired
	private UtilityObjectConversion utilityObjectConversion;

	/**
	 *
	 */
	@Override
	public Optional<NewVehicleDTO> addNewVehicle(NewVehicleDTO newVehicleDto) {

		NewVehicleDTO newVehicleDTOAdP = INewVehicleService.AddNewVehicleUniqueProperty(newVehicleDto);
		Optional<NewVehicleDTO> vehicleDto = Optional.empty();
		Optional<NewVehicleDTO> checkVehicleTypeName = getByVehicleTypeAndName(newVehicleDTOAdP.getVehicleType(),
				newVehicleDto.getVehicleName());
		if (!checkVehicleTypeName.isPresent()) {
			// convert NewVehicled to NewVehicle using ObjectMapper
			NewVehicle newVeh = newVehicleRepository
					.save(utilityObjectConversion.convertNewVehDtoNewVeh(newVehicleDTOAdP));
			vehicleDto = Optional.of(utilityObjectConversion.convertNewVehToNewVehDto(newVeh));
		}
		return vehicleDto;
	}

	/**
	 *
	 */
	@Override
	public Optional<NewVehicleDTO> getByVehicleTypeAndName(String vehicleType, String vehicleName) {
		Optional<NewVehicleDTO> returnVehDto = Optional.empty();
		Optional<NewVehicle> newVehicle = newVehicleRepository.findByVehicleTypeAndVehicleName(vehicleType,
				vehicleName);
		if (newVehicle.isPresent()) {
			returnVehDto = Optional.of(utilityObjectConversion.convertNewVehToNewVehDto(newVehicle.get()));
		}
		return returnVehDto;
	}

	/**
	 *
	 */
	@Override
	public Optional<List<NewVehicleDTO>> getAllNewVehicles() {
		List<NewVehicle> newVehicleList = newVehicleRepository.findAll();
		// convert list of newVehicles to NewVehicleDTO
		Optional<List<NewVehicleDTO>> newVehicleDtoList = Optional.empty();
		if (!newVehicleList.isEmpty() && newVehicleList != null) {
			List<NewVehicleDTO> convnewVehicleDtoList = newVehicleList.stream()
					.map(newVehicle -> utilityObjectConversion.convertNewVehToNewVehDto(newVehicle))
					.collect(Collectors.toList());
			newVehicleDtoList = Optional.of(convnewVehicleDtoList);
		}
		return newVehicleDtoList;
	}

	/**
	 *
	 */
	@Override
	public Optional<NewVehicleDTO> getNewVehicleID(String vehicleId) {
		Optional<NewVehicleDTO> newVehicleDTO = Optional.empty();
		Optional<NewVehicle> vehicleIdOrVehicleUniqueNum = newVehicleRepository.findByVehicleId(vehicleId);
		if (vehicleIdOrVehicleUniqueNum.isPresent()) {
			NewVehicleDTO newVehicleDTODb = utilityObjectConversion
					.convertNewVehToNewVehDto(vehicleIdOrVehicleUniqueNum.get());
			newVehicleDTO = Optional.of(newVehicleDTODb);
		}

		return newVehicleDTO;
	}

	/**
	 *
	 */
	@Override
	public Optional<NewVehicleDTO> updateNewVehicleId(NewVehicleDTO newVehicleDTO, String vehicleId) {
		Optional<NewVehicleDTO> newVehicleID = getNewVehicleID(vehicleId);
		if (newVehicleID.isPresent()) {
			NewVehicle dbNv = newVehicleRepository
					.save(utilityObjectConversion.convertNewVehDtoNewVeh(newVehicleID.map(updNewVehDto -> {
						updNewVehDto.setVehicleType(newVehicleDTO.getVehicleType());
						updNewVehDto.setVehicleName(newVehicleDTO.getVehicleName());
						updNewVehDto.setVehicleColour(newVehicleDTO.getVehicleColour());
						updNewVehDto.setVehicleCost(newVehicleDTO.getVehicleCost());
						return updNewVehDto;
					}).get()));
			newVehicleID = Optional.of(utilityObjectConversion.convertNewVehToNewVehDto(dbNv));
		}
		return newVehicleID;
	}
}
