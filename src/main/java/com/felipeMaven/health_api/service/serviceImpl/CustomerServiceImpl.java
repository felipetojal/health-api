package com.felipeMaven.health_api.service.serviceImpl;

import com.felipeMaven.health_api.domain.dto.customerDto.CustomerRequestDto;
import com.felipeMaven.health_api.domain.dto.customerDto.CustomerResponseDto;
import com.felipeMaven.health_api.domain.entity.Customer;
import com.felipeMaven.health_api.domain.mapper.CustomerMapper;
import com.felipeMaven.health_api.exception.CustomerNotFoundException;
import com.felipeMaven.health_api.repository.CustomerRepository;
import com.felipeMaven.health_api.service.serviceInterface.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Transactional
    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {

        Customer newCustomer = customerMapper.toEntity(customerRequestDto);
        customerRepository.save(newCustomer);
        return customerMapper.toResponse(newCustomer);

    }

    @Transactional
    @Override
    public CustomerResponseDto updateCustomerById(Long id, CustomerRequestDto customerRequestDto) {

        // Retrieving the customer from the database
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " was not found!"));

        // Updating the customer
        customerMapper.updateCustomerEntityFromDto(existingCustomer, customerRequestDto);

        // Saving the update to the database
        customerRepository.save(existingCustomer);

        // Returning the CustomerResponseDto of the operation
        return customerMapper.toResponse(existingCustomer);

    }

    @Override
    public Set<CustomerResponseDto> listAllCustomers() {

        return customerRepository.findAll().stream()
                .map(customerMapper::toResponse)
                .collect(Collectors.toSet());

    }

    @Transactional
    @Override
    public CustomerResponseDto getCustomerById(Long id) {

        return customerMapper.toResponse(
                customerRepository.findById(id)
                        .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " was not found!")));

    }

    @Transactional
    @Override
    public void deleteCustomerById(Long id) {

        // Validating the ID
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer with id " + id + " was not found");
        }

        // Deleting in the database
        customerRepository.deleteById(id);

    }
}
