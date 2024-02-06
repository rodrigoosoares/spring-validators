package com.personal.validator.valiations;

import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class StatusValidatorTest {


    private StatusValidator statusValidator;

    @BeforeEach
    void setup() {

        statusValidator = new StatusValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = { "HEALTH", "POISONED", "HUNGRY", "TIRED", "FREEZE" })
    void should_ReturnTrue_WhenReceiveAValidStatus(final String status) {

        final ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

        final boolean result = statusValidator.isValid(status, constraintValidatorContext);

        assertThat(result, is(true));

        verifyNoInteractions(constraintValidatorContext);

    }

    @Test
    void should_ReturnFalseWithTheNullMessage_WhenReceiveANullStatus() {

        final ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);
        final ConstraintViolationBuilder constraintViolationBuilder = mock(ConstraintViolationBuilder.class);

        when(constraintValidatorContext.buildConstraintViolationWithTemplate("cannot be null."))
                .thenReturn(constraintViolationBuilder);

        final boolean result = statusValidator.isValid(null, constraintValidatorContext);

        assertThat(result, is(false));

        verify(constraintValidatorContext).disableDefaultConstraintViolation();
        verify(constraintValidatorContext).buildConstraintViolationWithTemplate("cannot be null.");
        verify(constraintViolationBuilder).addConstraintViolation();

    }

    @Test
    void should_ReturnFalseWithTheNullMessage_WhenReceiveAnInvalidStatus() {

        final ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);
        final ConstraintViolationBuilder constraintViolationBuilder = mock(ConstraintViolationBuilder.class);

        when(constraintValidatorContext.buildConstraintViolationWithTemplate("'HEAT' is not a valid value. Possible values are: [HEALTH, POISONED, HUNGRY, TIRED, FREEZE]"))
                .thenReturn(constraintViolationBuilder);

        final boolean result = statusValidator.isValid("HEAT", constraintValidatorContext);

        assertThat(result, is(false));

        verify(constraintValidatorContext).disableDefaultConstraintViolation();
        verify(constraintValidatorContext).buildConstraintViolationWithTemplate("'HEAT' is not a valid value. Possible values are: [HEALTH, POISONED, HUNGRY, TIRED, FREEZE]");
        verify(constraintViolationBuilder).addConstraintViolation();

    }

}