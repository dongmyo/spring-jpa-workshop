package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // NOTE #10: Entity Graph 사용 - @EntityGraph

    @EntityGraph("orderWithCustomer")
    List<Order> getAllBy();

    @EntityGraph("orderWithOrderItems")
    List<Order> readAllBy();

    @EntityGraph("orderWithCustomerAndOrderItems")
    List<Order> queryAllBy();

    @EntityGraph("orderWithCustomerAndOrderItemsAndItem")
    List<Order> findAllBy();

}
