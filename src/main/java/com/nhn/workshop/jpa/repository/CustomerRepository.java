package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
