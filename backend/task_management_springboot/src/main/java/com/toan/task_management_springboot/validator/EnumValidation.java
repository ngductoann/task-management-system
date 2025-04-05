package com.toan.task_management_springboot.validator;

import java.util.Arrays;

import com.toan.task_management_springboot.annotaion.EnumConstraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator class for the {@code EnumConstraint} annotation.
 * 
 * This class implements the {@code ConstraintValidator} interface and provides
 * the logic to validate whether a given string value matches one of the
 * constants
 * in a specified enum class.
 * 
 * Validation logic:
 * - If the value is {@code null}, the validation passes (to allow for other
 * annotations like {@code @NotNull} to handle null checks).
 * - If the value is not null, it checks whether the value matches
 * (case-insensitively)
 * any of the enum constants in the specified enum class.
 * 
 * Example usage:
 * 
 * {@code
 * public enum Status {
 *     OPEN, IN_PROGRESS, COMPLETED;
 * }
 * 
 * public class Task {
 * @EnumConstraint(enumClass = Status.class, message = "Invalid status value")
 * private String status;
 * }
 * }
 * 
 * @author Your
 * @see com.toan.task_management_springboot.annotaion.EnumConstraint
 */
public class EnumValidation implements ConstraintValidator<EnumConstraint, String> {
    private Class<? extends Enum<?>> enumClass;

    /**
     * Initializes the validator by retrieving the enum class specified in the
     * {@code EnumConstraint} annotation.
     * 
     * @param constraintAnnotation the annotation instance containing the enum class
     */
    @Override
    public void initialize(EnumConstraint constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    /**
     * Validates whether the given value matches one of the constants in the
     * specified
     * enum class.
     * 
     * @param value   the value to validate
     * @param context the context in which the constraint is evaluated
     * @return {@code true} if the value is valid or {@code null}; {@code false}
     *         otherwise
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equalsIgnoreCase(value));
    }
}
