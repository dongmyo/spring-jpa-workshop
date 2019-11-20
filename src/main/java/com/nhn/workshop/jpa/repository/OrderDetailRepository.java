package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
