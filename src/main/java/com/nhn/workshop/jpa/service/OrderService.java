package com.nhn.workshop.jpa.service;

import com.nhn.workshop.jpa.entity.Order;
import com.nhn.workshop.jpa.entity.OrderDetail;
import com.nhn.workshop.jpa.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class OrderService {
    private final OrderDetailRepository orderDetailRepository;


    public OrderService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }


    @Transactional
    public void createOrderWithDetails() {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setOrder(order);
        orderDetail1.setType("type1");
        orderDetail1.setDescription("order1-type1");

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setOrder(order);
        orderDetail2.setType("type2");
        orderDetail2.setDescription("order1-type2");

        orderDetailRepository.saveAll(Arrays.asList(orderDetail1, orderDetail2));
    }

}
