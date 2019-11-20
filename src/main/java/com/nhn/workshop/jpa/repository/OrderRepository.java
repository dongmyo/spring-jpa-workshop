package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order as o "
           + "left join fetch o.details as od ")
    List<Order> getOrderWithDetails();

}
