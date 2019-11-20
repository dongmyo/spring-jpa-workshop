package com.nhn.workshop.jpa.service;

import com.nhn.workshop.jpa.entity.Order;
import com.nhn.workshop.jpa.entity.OrderDetail;
import com.nhn.workshop.jpa.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Transactional
    public void createOrderWithDetails() {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order = orderRepository.save(order);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setOrderId(order.getOrderId());
        orderDetail1.setType("type1");
        orderDetail1.setDescription("order1-type1");

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setOrderId(order.getOrderId());
        orderDetail2.setType("type2");
        orderDetail2.setDescription("order1-type2");

        order.getDetails().add(orderDetail1);
        order.getDetails().add(orderDetail2);
    }

}
