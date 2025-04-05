package com.toan.task_management_springboot.exception;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.toan.task_management_springboot.dto.ErrorDTO;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Global exception handler for the application.
 * 
 * This class provides centralized exception handling for all controllers
 * using the {@code @ControllerAdvice} annotation. It extends
 * {@link ResponseEntityExceptionHandler} to handle specific exceptions
 * and provides custom handling for general exceptions.
 * 
 * Features:
 * - Handles generic exceptions and returns a standardized error response.
 * - Handles validation errors for method arguments and returns detailed error
 * messages.
 * 
 * The error responses are encapsulated in the {@link ErrorDTO} class, which
 * includes
 * details such as the timestamp, HTTP status code, request path, and error
 * messages.
 * 
 * This class logs all exceptions for debugging purposes.
 * 
 * @author Your
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handles generic exceptions and returns a standardized error response.
	 * 
	 * @param request the HTTP request that caused the exception
	 * @param ex      the exception that occurred
	 * @return an {@link ErrorDTO} containing error details
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorDTO handleException(HttpServletRequest request, Exception ex) {
		ErrorDTO errorDTO = new ErrorDTO(
				new Date(System.currentTimeMillis()),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				request.getServletPath(),
				new ArrayList<>(Arrays.asList(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())));

		LOGGER.error(ex.getMessage(), ex);

		return errorDTO;
	}

	/**
	 * Handles validation errors for method arguments and returns detailed error
	 * messages.
	 * 
	 * This method is invoked when a method argument annotated with validation
	 * constraints
	 * fails validation. It collects all validation errors and returns them in a
	 * structured
	 * format.
	 * 
	 * @param ex      the exception containing validation errors
	 * @param headers the HTTP headers
	 * @param status  the HTTP status code
	 * @param request the web request that caused the exception
	 * @return a {@link ResponseEntity} containing an {@link ErrorDTO} with error
	 *         details
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorDTO errorDTO = new ErrorDTO(
				new Date(System.currentTimeMillis()),
				HttpStatus.BAD_REQUEST.value(),
				((ServletWebRequest) request).getRequest().getServletPath(),
				new ArrayList<>());

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		fieldErrors.forEach(fieldError -> {
			errorDTO.addError(fieldError.getDefaultMessage());
		});

		return new ResponseEntity<>(errorDTO, headers, status);
	}
}
