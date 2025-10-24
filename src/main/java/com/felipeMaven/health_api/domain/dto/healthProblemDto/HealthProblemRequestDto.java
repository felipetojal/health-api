package com.felipeMaven.health_api.domain.dto.healthProblemDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HealthProblemRequestDto (
        @NotBlank(message = "Name cannot be blank!")
        String name,

        @NotNull(message = "Severity cannot be null!")
        @Min(value = 1, message = "Severity cannot be lower than 1!")
        @Max(value = 2, message = "Severity cannot be higher than 2!")
        Integer severity
) {}
