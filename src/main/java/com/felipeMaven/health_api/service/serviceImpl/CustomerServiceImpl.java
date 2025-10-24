package com.felipeMaven.health_api.service.serviceImpl;

import com.felipeMaven.health_api.repository.CustomerRepository;
import com.felipeMaven.health_api.service.serviceInterface.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
}
