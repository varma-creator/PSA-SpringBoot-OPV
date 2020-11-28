package com.psa.opv.newvehicle.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.psa.opv.newvehicle.generator.NewVehicleCustomIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "New_Vehicle")
public class NewVehicle {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newvehicle_sql")
	@GenericGenerator(name = "newvehicle_sql", strategy = "com.psa.opv.newvehicle.generator.NewVehicleCustomIdGenerator", parameters = {
			@Parameter(name = NewVehicleCustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = NewVehicleCustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "New_Veh_"),
			@Parameter(name = NewVehicleCustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	@Id
	@Column(name = "vehicle_id")
	private String vehicleId;

	@Column(name = "vehicle_type")
	private String vehicleType;

	@Column(name = "vehicle_name")
	private String vehicleName;

	@Column(name = "vehicle_colour")
	private String vehicleColour;

	@Column(name = "vehicle_cost")
	private Double vehicleCost;

	@Column(name = "vehicle_mdate")
	private LocalDate vehicleManfDate;

	@Column(name = "vehicle_uniquenum", unique = true, nullable = false)
	private String vehicleUniqueNum;

}
