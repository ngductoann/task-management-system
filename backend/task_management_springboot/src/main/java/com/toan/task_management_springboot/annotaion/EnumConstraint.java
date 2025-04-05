package com.toan.task_management_springboot.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.toan.task_management_springboot.validator.EnumValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Custom annotation for validating that a field or parameter contains a value
 * that matches one of the constants in a specified enum class.
 * 
 * This annotation integrates with Jakarta Bean Validation and uses the
 * {@link EnumValidation} class to perform the validation logic.
 * 
 * Usage example:
 * 
 * public enum Status {
 * OPEN, IN_PROGRESS, COMPLETED;
 * }
 * 
 * public class Task {
 * {@code @EnumConstraint(enumClass = Status.class, message = "Invalid status value")}
 * private String status;
 * }
 * 
 * Annotation elements:
 * 
 * message: Custom error message when validation fails.
 * groups: Used for grouping constraints (part of Jakarta Validation * API).
 * payload: Used to carry metadata information about the validation.
 * enumClass: Specifies the enum class to validate against (required).
 * 
 * @author Your Name
 * @see EnumValidation
 */
@Constraint(validatedBy = EnumValidation.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumConstraint {
    /**
     * The error message to be returned when validation fails.
     * 
     * @return the error message
     */
    String message() default "Invalid enum value";

    /**
     * Groups for constraint validation.
     * 
     * @return the groups
     */
    Class<?>[] groups() default {};

    /**
     * Payload for clients to specify additional metadata.
     * 
     * @return the payload
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * The enum class against which the value will be validated.
     * 
     * @return the enum class
     */
    Class<? extends Enum<?>> enumClass();
}
