package com.psa.opv.newvehicle.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.psa.opv.newvehicle.dto.NewVehicleDTO;
import com.psa.opv.newvehicle.entity.NewVehicle;
import com.psa.opv.newvehicle.repository.NewVehicleRepository;
import com.psa.opv.newvehicle.service.INewVehicleService;
import com.psa.opv.newvehicle.utility.UtilityObjectConversion;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Varma
 *
 */

@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "newVehicelCache")
@Slf4j
public class NewVehicleService implements INewVehicleService {

	@Autowired
	private NewVehicleRepository newVehicleRepository;

	@Autowired
	private UtilityObjectConversion utilityObjectConversion;

	/**
	 *
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
	public Optional<NewVehicleDTO> addNewVehicle(NewVehicleDTO newVehicleDto) {
        log.info("addNewVehicle()->{}",newVehicleDto);
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
	@Cacheable(key="#vehicleId")
	public Optional<NewVehicleDTO> getNewVehicleID(String vehicleId) {
		log.info("getNewVehicleID()->{}",vehicleId);
		Optional<NewVehicleDTO> newVehicleDTO = Optional.empty();
		Optional<NewVehicle> vehicleIdOrVehicleUniqueNum = newVehicleRepository.findByVehicleId(vehicleId);
		if (vehicleIdOrVehicleUniqueNum.isPresent()) {
			NewVehicleDTO newVehicleDTODb = utilityObjectConversion
					.convertNewVehToNewVehDto(vehicleIdOrVehicleUniqueNum.get());
			newVehicleDTO = Optional.of(newVehicleDTODb);
			log.debug("successfully fetching vehicle from db:"+vehicleId);
		}

		return newVehicleDTO;
	}

	/**
	 *
	 */
	@Override
	@CachePut(key = "#vehicleId")
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
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

	/**
	 *
	 */
	@Override
	@CacheEvict(key="#vehicleId")
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
	public Optional<NewVehicleDTO> deleteByVehicleID(String vehicleId) {
		Optional<NewVehicleDTO> emptyNewVehicleDTO = Optional.empty();
		List<NewVehicle> deleteByVehicleId = newVehicleRepository.removeByVehicleId(vehicleId);
		if (!deleteByVehicleId.isEmpty() && deleteByVehicleId != null) {
			NewVehicleDTO convertNewVehToNewVehDto = utilityObjectConversion
					.convertNewVehToNewVehDto(deleteByVehicleId.get(0));
			emptyNewVehicleDTO = Optional.of(convertNewVehToNewVehDto);
		}
		return emptyNewVehicleDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
	public Optional<List<NewVehicleDTO>> removeByVehicleType(String vehicleId) {
		List<NewVehicle> removeByVehicleType = newVehicleRepository.removeByVehicleType(vehicleId);
		Optional<List<NewVehicleDTO>> newVehicleDtoList = Optional.empty();
		if (!removeByVehicleType.isEmpty() && removeByVehicleType != null) {
			List<NewVehicleDTO> collect = removeByVehicleType.stream()
					.map(newVeh -> utilityObjectConversion.convertNewVehToNewVehDto(newVeh))
					.collect(Collectors.toList());
			newVehicleDtoList = Optional.of(collect);
		}
		return newVehicleDtoList;
	}

	@Override
	public Optional<List<NewVehicleDTO>> getByVehicleTypeAndVehicleColour(String vehicleType, String vehicleColour) {
		Optional<List<NewVehicle>> byVehicleTypeAndVehicleColour = newVehicleRepository
				.getByVehicleTypeAndVehicleColour(vehicleType, vehicleColour);
		Optional<List<NewVehicleDTO>> newVehicleDtoList = Optional.empty();
		if (!byVehicleTypeAndVehicleColour.get().isEmpty() && byVehicleTypeAndVehicleColour.get() != null) {
			List<NewVehicleDTO> collect = byVehicleTypeAndVehicleColour.get().stream()
					.map(newVeh -> utilityObjectConversion.convertNewVehToNewVehDto(newVeh))
					.collect(Collectors.toList());
			newVehicleDtoList = Optional.of(collect);
		}
		return newVehicleDtoList;
	}
}
