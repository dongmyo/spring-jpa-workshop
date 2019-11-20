package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
