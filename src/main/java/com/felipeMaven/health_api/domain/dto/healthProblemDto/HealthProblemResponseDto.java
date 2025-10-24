package com.felipeMaven.health_api.domain.dto.healthProblemDto;

import lombok.Builder;

@Builder
public record HealthProblemResponseDto (
        Long id,
        String name,
        Integer severity
) {}
