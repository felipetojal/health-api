package com.felipeMaven.health_api.domain.dto.customerDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felipeMaven.health_api.domain.dto.healthProblemDto.HealthProblemRequestDto;
import com.felipeMaven.health_api.domain.enums.Sex;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.Set;

public record CustomerRequestDto(
        @NotBlank(message = "Name cannot be blank!")    // Field cannot be: null, empty or made only by blank spaces
        String name,

        @NotNull(message = "Sex cannot be null!")
        Sex sex,

        @NotNull(message = "BirthDate cannot be null!")
        @Past(message = "BirthDate must be in the past!")
        @JsonFormat(pattern = "yyyy/MM/dd")
        LocalDate birthDate,

        @Valid  // Checks for the HealthProblemRequestDto validations
        Set<HealthProblemRequestDto> healthProblems
) {}
