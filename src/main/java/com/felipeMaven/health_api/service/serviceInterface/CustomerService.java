package com.felipeMaven.health_api.service.serviceInterface;

import com.felipeMaven.health_api.domain.dto.customerDto.CustomerRequestDto;
import com.felipeMaven.health_api.domain.dto.customerDto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    /**
     * Method to add a new customer
     * @param customerRequestDto
     * @return
     */
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

    /**
     * Method to update a customer
     * @param customerRequestDto
     * @return
     */
    public CustomerResponseDto updateCustomer(CustomerRequestDto customerRequestDto);

    /**
     * Method to list all customers
     * @return
     */
    public List<CustomerResponseDto> listAllCustomers();

    /**
     * Method to get customer by id
     * @param id
     * @return
     */
    public CustomerResponseDto getCustomerById(Long id);

    /**
     * Method to delete customer by id
     * @param id
     */
    public void deleteCustomerById(Long id);

}
