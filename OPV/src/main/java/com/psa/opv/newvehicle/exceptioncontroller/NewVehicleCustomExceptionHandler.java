package com.psa.opv.newvehicle.exceptioncontroller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.psa.opv.newvehicle.constants.NewVehicleConstants;
import com.psa.opv.newvehicle.error.JsonErrorDetails;
import com.psa.opv.newvehicle.exception.NewVehicleAlreardyFoundException;
import com.psa.opv.newvehicle.exception.NewVehiclesNotFoundException;

/**
 * @author USER
 *
 */
@ControllerAdvice
public class NewVehicleCustomExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NewVehicleAlreardyFoundException.class)
	public ResponseEntity<JsonErrorDetails> handlesVNVehicleAlreadyFoundException(
			NewVehicleAlreardyFoundException exception, WebRequest request) {
		JsonErrorDetails jsonErrorDetails = new JsonErrorDetails(LocalDateTime.now(), HttpStatus.CONFLICT.value(),
				NewVehicleConstants.CONFLICT_REQUEST, exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<JsonErrorDetails>(jsonErrorDetails, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NewVehiclesNotFoundException.class)
	public ResponseEntity<JsonErrorDetails> handlesVNVehicleNotFoundException(NewVehiclesNotFoundException exception,
			WebRequest request) {
		JsonErrorDetails jsonErrorDetails = new JsonErrorDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				NewVehicleConstants.NOT_FOUND, exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<JsonErrorDetails>(jsonErrorDetails, HttpStatus.NOT_FOUND);
	}

}
