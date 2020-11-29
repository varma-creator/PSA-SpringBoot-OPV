package com.psa.opv.newvehicle.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.psa.opv.newvehicle.entity.NewVehicle;

/**
 * @author varma
 *
 */
public interface NewVehicleRepository extends JpaRepository<NewVehicle, Integer> {

	/**
	 * @param vehicleType
	 * @param vehicelName
	 * @return
	 */
	public Optional<NewVehicle> findByVehicleTypeAndVehicleName(String vehicleType, String vehicelName);

	/**
	 * @param vehicleId
	 * @return
	 */
	public Optional<NewVehicle> findByVehicleId(String vehicleId);

	/**
	 * Delete operation by using unique id
	 * 
	 * @param vehicleId
	 * @return
	 */
	@Transactional
	public List<NewVehicle> removeByVehicleId(String vehicleId);

	/**
	 * Deleting all records by using vehicle type
	 * 
	 * @param vehicleId
	 * @return
	 */
	@Transactional
	public List<NewVehicle> removeByVehicleType(String vehicleId);

	/**
	 * @param vehicleColour
	 * @return
	 */
	public Optional<List<NewVehicle>> getByVehicleTypeAndVehicleColour(String vehicleType, String vehicleColour);
}
