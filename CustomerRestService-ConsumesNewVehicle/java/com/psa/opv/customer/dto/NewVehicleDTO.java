package com.psa.opv.customer.dto;

import java.time.LocalDate;


public class NewVehicleDTO {
	private String vehicleId;
	
	private String vehicleType;

	private String vehicleName;

	private String vehicleColour;

	private Double vehicleCost;

	private LocalDate vehicleManfDate;
	
	private String vehicleUniqueNum;

	public NewVehicleDTO() {
		super();
	}

	public NewVehicleDTO(String vehicleId, String vehicleType, String vehicleName, String vehicleColour,
			Double vehicleCost, LocalDate vehicleManfDate, String vehicleUniqueNum) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleType = vehicleType;
		this.vehicleName = vehicleName;
		this.vehicleColour = vehicleColour;
		this.vehicleCost = vehicleCost;
		this.vehicleManfDate = vehicleManfDate;
		this.vehicleUniqueNum = vehicleUniqueNum;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleColour() {
		return vehicleColour;
	}

	public void setVehicleColour(String vehicleColour) {
		this.vehicleColour = vehicleColour;
	}

	public Double getVehicleCost() {
		return vehicleCost;
	}

	public void setVehicleCost(Double vehicleCost) {
		this.vehicleCost = vehicleCost;
	}

	public LocalDate getVehicleManfDate() {
		return vehicleManfDate;
	}

	public void setVehicleManfDate(LocalDate vehicleManfDate) {
		this.vehicleManfDate = vehicleManfDate;
	}

	public String getVehicleUniqueNum() {
		return vehicleUniqueNum;
	}

	public void setVehicleUniqueNum(String vehicleUniqueNum) {
		this.vehicleUniqueNum = vehicleUniqueNum;
	}

	@Override
	public String toString() {
		return "NewVehicleDTO [vehicleId=" + vehicleId + ", vehicleType=" + vehicleType + ", vehicleName=" + vehicleName
				+ ", vehicleColour=" + vehicleColour + ", vehicleCost=" + vehicleCost + ", vehicleManfDate="
				+ vehicleManfDate + ", vehicleUniqueNum=" + vehicleUniqueNum + "]";
	}
}

