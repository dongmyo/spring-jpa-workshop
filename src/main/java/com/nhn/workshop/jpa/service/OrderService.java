package com.nhn.workshop.jpa.service;

import com.nhn.workshop.jpa.entity.Order;
import com.nhn.workshop.jpa.entity.OrderDetail;
import com.nhn.workshop.jpa.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setOrder(order);
        orderDetail1.setType("type1");
        orderDetail1.setDescription("order1-type1");

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setOrder(order);
        orderDetail2.setType("type2");
        orderDetail2.setDescription("order1-type2");

        order.getDetails().add(orderDetail1);
        order.getDetails().add(orderDetail2);

        orderRepository.save(order);

        Order order2 = new Order();
        order2.setOrderDate(LocalDateTime.now());

        OrderDetail orderDetail3 = new OrderDetail();
        orderDetail3.setOrder(order2);
        orderDetail3.setType("type1");
        orderDetail3.setDescription("order2-type1");

        order2.getDetails().add(orderDetail3);

        orderRepository.save(order2);
    }

    @Transactional(readOnly = true)
    public List<String> getOrdersDescriptions() {
        // NOTE: select * from Orders
        return orderRepository.findAll()
                              .stream()
                              .map(Order::getDetails)
                              .flatMap(Collection::stream)
                              .map(OrderDetail::getDescription)
                              .filter(Objects::nonNull)
                              .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<String> readOrdersDescriptions() {
        // NOTE: select o.*, od.* from Orders as o left join OrderDetails od on o.order_id = od.order_id
        return orderRepository.getOrderWithDetails()
                              .stream()
                              .map(Order::getDetails)
                              .flatMap(Collection::stream)
                              .map(OrderDetail::getDescription)
                              .filter(Objects::nonNull)
                              .collect(Collectors.toList());
    }

}
