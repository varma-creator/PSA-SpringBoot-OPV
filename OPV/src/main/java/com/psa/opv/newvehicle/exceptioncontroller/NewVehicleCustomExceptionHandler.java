package com.psa.opv.newvehicle.exceptioncontroller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	/**
	 * Handling the Internal server error
	 * 
	 * @param exception
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<JsonErrorDetails> handleInternalServerError(Exception exception, WebRequest webRequest) {
		JsonErrorDetails jsonErrorDetails = new JsonErrorDetails(LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), NewVehicleConstants.INTERNAL_SERVER_ERROR,
				exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<JsonErrorDetails>(jsonErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handling method parameter using @Valid annotation
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		JsonErrorDetails jsonErrorDetails = new JsonErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				NewVehicleConstants.VALIDATION_FIELD, details.toString(), request.getDescription(false));
		return new ResponseEntity<>(jsonErrorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * validating path variable and request @param
	 * 
	 * @param exception
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<JsonErrorDetails> constraintViolationException(ConstraintViolationException exception,
			WebRequest webRequest) {
		JsonErrorDetails jsonErrorDetails = new JsonErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				NewVehicleConstants.VALIDATION_FIELD, exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<JsonErrorDetails>(jsonErrorDetails, HttpStatus.BAD_REQUEST);

	}
}
