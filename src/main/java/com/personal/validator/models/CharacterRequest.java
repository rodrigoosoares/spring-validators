package com.personal.validator.models;

import com.personal.validator.validators.ValidEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharacterRequest(

        @NotBlank
        @NotNull
        String name,

        @Min(0)
        Long healthPoints,

        @Min(0)
        Long attack,

        @Min(0)
        Long defense,

        @Min(0)
        Long stamina,

        @Min(0)
        Long intelligence,

        @ValidEnum(value = Status.class)
        String status
) {
}
