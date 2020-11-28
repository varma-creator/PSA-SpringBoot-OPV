package com.psa.opv.newvehicle.service;

import java.util.List;
import java.util.Optional;

import com.psa.opv.newvehicle.constants.NewVehicleConstants;
import com.psa.opv.newvehicle.dto.NewVehicleDTO;

/**
 * @author Varma
 *
 */
public interface INewVehicleService {

	public Optional<NewVehicleDTO> addNewVehicle(NewVehicleDTO newVehicleDto);

	public Optional<NewVehicleDTO> getByVehicleTypeAndName(String vehicleType, String vehicleName);

	public Optional<List<NewVehicleDTO>> getAllNewVehicles();

	public Optional<NewVehicleDTO> getNewVehicleID(String vehicleId);

	public Optional<NewVehicleDTO> updateNewVehicleId(NewVehicleDTO newVehicleDTO, String vehicleId);

	/**
	 * @param newVehicleDTO
	 * @return
	 */
	public static NewVehicleDTO AddNewVehicleUniqueProperty(NewVehicleDTO newVehicleDTO) {

		String newvehUniqNum = String.join(NewVehicleConstants.CONCAT_SYMBOL,
				newVehicleDTO.getVehicleType().substring(0, 2), newVehicleDTO.getVehicleName().substring(0, 2),
				newVehicleDTO.getVehicleColour().substring(0, 1));
		newVehicleDTO.setVehicleUniqueNum(newvehUniqNum);
		return newVehicleDTO;
	}
}
