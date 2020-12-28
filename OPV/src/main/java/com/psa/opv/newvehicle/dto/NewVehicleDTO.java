package com.psa.opv.newvehicle.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewVehicleDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String vehicleId;
	// @NotBlank annotation is used for not null(@NotNull) and not empty ,atleast
	// one
	// character

	@NotBlank(message = "vehicleType must not be empty")
	@JsonProperty
	private String vehicleType;

	@NotBlank(message = "vehicleName must not be empty")
	@JsonProperty
	private String vehicleName;

	@NotBlank(message = "vehicleColour must not be empty")
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
