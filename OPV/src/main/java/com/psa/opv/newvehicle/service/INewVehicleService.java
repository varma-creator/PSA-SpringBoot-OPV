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

	/**
	 * @param newVehicleDto
	 * @return NewVehicleDTO
	 */
	public Optional<NewVehicleDTO> addNewVehicle(NewVehicleDTO newVehicleDto);

	/**
	 * @param vehicleType
	 * @param vehicleName
	 * @return NewVehicleDTO
	 */
	public Optional<NewVehicleDTO> getByVehicleTypeAndName(String vehicleType, String vehicleName);

	/**
	 * @return List<NewVehicleDTO>
	 */
	public Optional<List<NewVehicleDTO>> getAllNewVehicles();

	/**
	 * @param vehicleId
	 * @return NewVehicleDTO
	 */
	public Optional<NewVehicleDTO> getNewVehicleID(String vehicleId);

	/**
	 * @param newVehicleDTO
	 * @param vehicleId
	 * @return Optional<NewVehicleDTO>
	 */
	public Optional<NewVehicleDTO> updateNewVehicleId(NewVehicleDTO newVehicleDTO, String vehicleId);

	/**
	 * @param vehicleId
	 * @return Optional<NewVehicleDTO>
	 */
	public Optional<NewVehicleDTO> deleteByVehicleID(String vehicleId);
	
	/**
	 * @param vehicleId
	 * @return Optional<List<NewVehicleDTO>>
	 */
	public Optional<List<NewVehicleDTO>> removeByVehicleType(String vehicleId);
	
	/**
	 * @param vehicleType
	 * @param vehicleColour
	 * @return Optional<List<NewVehicleDTO>>
	 */
	public Optional<List<NewVehicleDTO>> getByVehicleTypeAndVehicleColour(String vehicleType,String vehicleColour);
	/**
	 * @param newVehicleDTO
	 * @return NewVehicleDTO
	 */
	public static NewVehicleDTO AddNewVehicleUniqueProperty(NewVehicleDTO newVehicleDTO) {

		String newvehUniqNum = String.join(NewVehicleConstants.CONCAT_SYMBOL,
				newVehicleDTO.getVehicleType().substring(0, 2), newVehicleDTO.getVehicleName().substring(0, 2),
				newVehicleDTO.getVehicleColour().substring(0, 1));
		newVehicleDTO.setVehicleUniqueNum(newvehUniqNum);
		return newVehicleDTO;
	}
}
