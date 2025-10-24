package com.felipeMaven.health_api.domain.mapper;

import com.felipeMaven.health_api.domain.dto.healthProblemDto.HealthProblemRequestDto;
import com.felipeMaven.health_api.domain.dto.healthProblemDto.HealthProblemResponseDto;
import com.felipeMaven.health_api.domain.entity.HealthProblem;
import org.springframework.stereotype.Component;

@Component
public class HealthProblemMapper {

    /**
     * Method to map a HealthProblemRequestDto to a HealthProblem entity
     * @param healthProblemRequestDto
     * @return
     */
    public HealthProblem toEntity(HealthProblemRequestDto healthProblemRequestDto) {

        return HealthProblem.builder()
                .healthProblemName(healthProblemRequestDto.name())
                .healthProblemSeverity(healthProblemRequestDto.severity())
                .build();

    }

    /**
     * Method to map a HealthProblem entity to a HealthProblemResponseDto
     * @param healthProblem
     * @return
     */
    public HealthProblemResponseDto toResponse(HealthProblem healthProblem) {

        return HealthProblemResponseDto.builder()
                .id(healthProblem.getId())
                .name(healthProblem.getHealthProblemName())
                .severity(healthProblem.getHealthProblemSeverity())
                .build();

    }

}
