package com.felipeMaven.health_api.domain.mapper;

import com.felipeMaven.health_api.domain.dto.customerDto.CustomerRequestDto;
import com.felipeMaven.health_api.domain.dto.customerDto.CustomerResponseDto;
import com.felipeMaven.health_api.domain.dto.healthProblemDto.HealthProblemRequestDto;
import com.felipeMaven.health_api.domain.dto.healthProblemDto.HealthProblemResponseDto;
import com.felipeMaven.health_api.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    @Autowired
    HealthProblemMapper healthProblemMapper;

    /**
     * Method to convert a CustomerRequestDto to an entity
     * @param customerRequestDto
     * @return
     */
    public Customer toEntity(CustomerRequestDto customerRequestDto) {

        Customer customer = Customer.builder()
                .name(customerRequestDto.name())
                .sex(customerRequestDto.sex())
                .birthDate(customerRequestDto.birthDate())
                .build();

        /**
         * The healthProblem field can be null. So we need to verify it before streaming.
         */
        if (customerRequestDto.healthProblems() != null) {
            copyHealthProblemsFromDtoToEntity(customerRequestDto.healthProblems(), customer);
        }

        return customer;

    }

    /**
     * Method to map and copy the HealthProblemRequestDto set to HealthProblem in Customer
     * @param healthProblemRequestDtos
     * @param customer
     */
    private void copyHealthProblemsFromDtoToEntity(Set<HealthProblemRequestDto> healthProblemRequestDtos, Customer customer) {

        customer.setHealthProblems(healthProblemRequestDtos.stream()
                .map(dto -> healthProblemMapper.toEntity(dto))
                /**
                 * HealthProblem has the Foreign Key.
                 * It is mandatory to fill the 'customer' field for every HealthProblem
                 * before saving, so that JPA inserts the correct customer_id.
                 * 'peek' is responsible for doing that
                 */
                .peek(healthProblem -> healthProblem.setCustomer(customer))
                .collect(Collectors.toSet()));

    }

    /**
     * Method to convert a Customer entity to a CustomerResponseDto
     * @param customer
     * @return
     */
    public CustomerResponseDto toResponse(Customer customer) {

        Set<HealthProblemResponseDto> healthProblemResponseDtos = customer.getHealthProblems().stream()
                .map(healthProblemMapper::toResponse)
                .collect(Collectors.toSet());
        
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .sex(customer.getSex())
                .name(customer.getName())
                .birthDate(customer.getBirthDate())
                .healthProblems(healthProblemResponseDtos)
                .creationDate(customer.getCreationDate())
                .lastUpdateDate(customer.getLastUpdateDate())
                .build();

    }

    public void updateCustomerEntityFromDto(Customer customerToUpdate, CustomerRequestDto customerRequestDto) {

        // Updating each field of the Customer
        customerToUpdate.setName(customerRequestDto.name());
        customerToUpdate.setSex(customerRequestDto.sex());
        customerToUpdate.setBirthDate(customerRequestDto.birthDate());

        // Updating the HealthProblemSet
        if (customerRequestDto.healthProblems() != null) {

            // Cleaning the existing collection
            customerToUpdate.getHealthProblems().clear();

            // Copying the new HealthProblemSet
            copyHealthProblemsFromDtoToEntity(customerRequestDto.healthProblems(), customerToUpdate);
        }

    }
}
