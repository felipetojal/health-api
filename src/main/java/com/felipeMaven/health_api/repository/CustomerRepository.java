package com.felipeMaven.health_api.repository;

import com.felipeMaven.health_api.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);

}
