package com.felipeMaven.health_api.repository;

import com.felipeMaven.health_api.domain.entity.HealthProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthProblemRepository extends JpaRepository<HealthProblem, Long> {
}
