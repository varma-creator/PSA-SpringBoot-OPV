package com.psa.opv.newvehicle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psa.opv.newvehicle.entity.NewVehicle;

public interface NewVehicleRepository extends JpaRepository<NewVehicle, Integer> {

	public Optional<NewVehicle> findByVehicleTypeAndVehicleName(String vehicleType, String vehicelName);

	public Optional<NewVehicle> findByVehicleId(String vehicleId);
}
