package com.personal.validator.valiations;

import com.personal.validator.models.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Objects;

public class StatusValidator implements ConstraintValidator<ValidStatus, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        boolean isValid = true;
        String errorMessage = null;

        if(Objects.isNull(value)) {
            isValid = false;
            errorMessage = "cannot be null.";
        } else if(isInvalid(value)) {
            isValid = false;
            errorMessage = String.format("'%s' is not a valid value. Possible values are: %s", value, Arrays.toString(Status.values()));
        }

        if(!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
        }

        return isValid;
    }

    private boolean isInvalid(String value) {

        return Arrays.stream(Status.values()).map(Status::name).noneMatch(value::equals);
    }
}
