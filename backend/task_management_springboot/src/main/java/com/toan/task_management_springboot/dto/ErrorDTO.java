package com.toan.task_management_springboot.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for encapsulating error details in a standardized
 * format.
 * 
 * This class is used to represent error responses returned by the application's
 * exception handlers. It includes details such as the timestamp of the error,
 * the HTTP status code, the request path, and a list of error messages.
 * 
 * Features:
 * - Provides a structured format for error responses.
 * - Includes utility methods for adding error messages.
 * 
 * Lombok annotations are used to generate boilerplate code such as getters,
 * setters, constructors, and more.
 * 
 * @author Your
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

	/**
	 * The timestamp indicating when the error occurred.
	 */
	private Date timestamp;

	/**
	 * The HTTP status code associated with the error.
	 */
	private int status;

	/**
	 * The path of the HTTP request that caused the error.
	 */
	private String path;

	/**
	 * A list of error messages providing details about the error.
	 */
	private List<String> errors = new ArrayList<>();

	/**
	 * Adds an error message to the list of errors.
	 * 
	 * @param message the error message to add
	 */
	public void addError(String message) {
		this.errors.add(message);
	}
}
