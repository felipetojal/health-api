package com.felipeMaven.health_api.service.serviceImpl;

import com.felipeMaven.health_api.repository.HealthProblemRepository;
import com.felipeMaven.health_api.service.serviceInterface.HealthProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthProblemServiceImpl implements HealthProblemService {

    @Autowired
    private HealthProblemRepository healthProblemRepository;
}
