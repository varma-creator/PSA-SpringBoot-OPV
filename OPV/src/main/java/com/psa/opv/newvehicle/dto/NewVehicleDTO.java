package com.psa.opv.newvehicle.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewVehicleDTO {
	@JsonProperty
	private String vehicleId;
	@NotNull
	@NotBlank
	@JsonProperty
	private String vehicleType;
	@NotNull
	@NotBlank
	@JsonProperty
	private String vehicleName;
	@NotNull
	@NotBlank
	@JsonProperty
	private String vehicleColour;
	@NotNull
	@JsonProperty()
	private Double vehicleCost;
	@NotNull
	@JsonProperty
	private LocalDate vehicleManfDate;
	@JsonProperty
	private String vehicleUniqueNum;

}
