package com.felipeMaven.health_api.controller;

import com.felipeMaven.health_api.domain.dto.customerDto.CustomerRequestDto;
import com.felipeMaven.health_api.domain.dto.customerDto.CustomerResponseDto;
import com.felipeMaven.health_api.service.serviceImpl.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
        CustomerResponseDto customerResponseDto = customerService.createCustomer(customerRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerResponseDto.id())
                .toUri();

        return ResponseEntity.created(location).body(customerResponseDto);
    }

    @GetMapping
    public ResponseEntity<Set<CustomerResponseDto>> getCustomerList() {

        Set<CustomerResponseDto> customerResponseDtoSet = customerService.listAllCustomers();

        // Returns 200 OK with the SET in the body
        return ResponseEntity.ok(customerResponseDtoSet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable @Valid Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable @Valid Long id, @RequestBody @Valid CustomerRequestDto customerRequestDto) {
        CustomerResponseDto customerResponseDto = customerService.updateCustomerById(id, customerRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponseDto);
    }

    @GetMapping("/{ id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto customerResponseDto = customerService.getCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponseDto);
    }
}
