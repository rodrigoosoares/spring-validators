package com.personal.validator.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {

    private List<String> validValues;

    @Override
    public void initialize(final ValidEnum constraintAnnotation) {

        Class<? extends Enum<?>> enumClazz = constraintAnnotation.value();

        validValues = Stream.of(enumClazz.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        boolean isValid = true;
        String errorMessage = null;

        if(Objects.isNull(value)) {
            isValid = false;
            errorMessage = "cannot be null.";
        }

        if(isInvalid(value)) {
            isValid = false;
            errorMessage = String.format("'%s' is not a valid value. Possible values are: %s", value, validValues);
        }

        if(!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
        }

        return isValid;
    }

    private boolean isInvalid(String value) {

        return validValues.stream().noneMatch(value::equals);
    }
}
