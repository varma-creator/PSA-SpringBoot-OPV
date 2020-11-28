package com.psa.opv.newvehicle.exception;

/**
 * @author USER
 *
 */
public class NewVehiclesNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewVehiclesNotFoundException(String message) {
		super(message);
	}

	public NewVehiclesNotFoundException() {
		super();
	}
}
