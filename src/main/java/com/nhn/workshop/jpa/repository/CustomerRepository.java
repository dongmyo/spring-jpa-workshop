package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

// NOTE #5: Customer Entity를 위한 Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
