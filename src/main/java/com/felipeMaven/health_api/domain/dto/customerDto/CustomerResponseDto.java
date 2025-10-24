package com.felipeMaven.health_api.domain.dto.customerDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felipeMaven.health_api.domain.dto.healthProblemDto.HealthProblemResponseDto;
import com.felipeMaven.health_api.domain.enums.Sex;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record CustomerResponseDto (
        Long id,
        String name,
        Sex sex,

        @JsonFormat(pattern = "yyyy/MM/dd")
        LocalDate birthDate,

        Set<HealthProblemResponseDto> healthProblems,

        @JsonFormat(pattern = "yyyy/MM/dd")
        LocalDate creationDate,

        @JsonFormat(pattern = "yyyy/MM/dd")
        LocalDate lastUpdateDate
) {}
