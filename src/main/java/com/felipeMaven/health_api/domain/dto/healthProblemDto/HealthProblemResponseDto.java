package com.felipeMaven.health_api.domain.dto.healthProblemDto;

public record HealthProblemResponseDto (
        Long id,
        String name,
        Integer severity
) {}
