package com.psa.opv.newvehicle.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psa.opv.newvehicle.dto.NewVehicleDTO;
import com.psa.opv.newvehicle.entity.NewVehicle;

@Component
@Scope(scopeName = "singleton")
public class UtilityObjectConversion {

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * convert NewVehicleDto to NewVehicle
	 * 
	 * @param newVehicleDTO
	 * @return
	 */
	public NewVehicle convertNewVehDtoNewVeh(NewVehicleDTO newVehicleDTO) {
		return objectMapper.convertValue(newVehicleDTO, NewVehicle.class);

	}

	/**
	 * convert NewVehicle to NewVehicleDto
	 * 
	 * @param newVehicle
	 * @return
	 */
	public NewVehicleDTO convertNewVehToNewVehDto(NewVehicle newVehicle) {
		return objectMapper.convertValue(newVehicle, NewVehicleDTO.class);

	}
}
