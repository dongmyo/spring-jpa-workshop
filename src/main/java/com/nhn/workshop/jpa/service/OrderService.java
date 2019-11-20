package com.nhn.workshop.jpa.service;

import com.nhn.workshop.jpa.entity.Customer;
import com.nhn.workshop.jpa.entity.Item;
import com.nhn.workshop.jpa.entity.Order;
import com.nhn.workshop.jpa.entity.OrderItem;
import com.nhn.workshop.jpa.repository.CustomerRepository;
import com.nhn.workshop.jpa.repository.ItemRepository;
import com.nhn.workshop.jpa.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;


    public OrderService(CustomerRepository customerRepository,
                        ItemRepository itemRepository,
                        OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }


    // NOTE #7: Customer, Item, Order, OrderItem 데이터 생성
    @Transactional
    public void createOrderWithDetails() {
        Item apple = new Item();
        apple.setItemName("apple");
        apple.setItemPrice(100L);
        apple = itemRepository.save(apple);

        Item orange = new Item();
        orange.setItemName("orange");
        orange.setItemPrice(200L);
        orange = itemRepository.save(orange);

        Item banana = new Item();
        banana.setItemName("banana");
        banana.setItemPrice(300L);
        banana = itemRepository.save(banana);

        Customer customer1 = new Customer();
        customer1.setCustomerName("customer1");
        customer1 = customerRepository.save(customer1);

        OrderItem orderItem1_1 = new OrderItem();
        orderItem1_1.setItem(apple);
        orderItem1_1.setQuantity(10L);

        OrderItem orderItem1_2 = new OrderItem();
        orderItem1_2.setItem(banana);
        orderItem1_2.setQuantity(2L);

        Order order1_1 = new Order();
        order1_1.setCustomer(customer1);
        order1_1.setOrderDate(LocalDateTime.now());
        order1_1.setOrderItems(Arrays.asList(orderItem1_1, orderItem1_2));
        orderRepository.save(order1_1);

        Customer customer2 = new Customer();
        customer2.setCustomerName("customer2");
        customer2 = customerRepository.save(customer2);

        OrderItem orderItem2_1 = new OrderItem();
        orderItem2_1.setItem(orange);
        orderItem2_1.setQuantity(3L);

        Order order2_1 = new Order();
        order2_1.setCustomer(customer2);
        order2_1.setOrderDate(LocalDateTime.now());
        order2_1.setOrderItems(Collections.singletonList(orderItem2_1));
        orderRepository.save(order2_1);

        OrderItem orderItem2_2 = new OrderItem();
        orderItem2_2.setItem(banana);
        orderItem2_2.setQuantity(5L);

        Order order2_2 = new Order();
        order2_2.setCustomer(customer2);
        order2_2.setOrderDate(LocalDateTime.now());
        order2_2.setOrderItems(Collections.singletonList(orderItem2_2));
        orderRepository.save(order2_2);
    }

    @Transactional(readOnly = true)
    public List<String> getOrdersByFindAll() {
        return getAllItemNames(orderRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<String> getOrdersWithCustomer() {
        return getAllItemNames(orderRepository.getAllBy());
    }

    @Transactional(readOnly = true)
    public List<String> getOrdersWithOrderItems() {
        return getAllItemNames(orderRepository.readAllBy());
    }

    @Transactional(readOnly = true)
    public List<String> getOrdersWithCustomerAndOrderItems() {
        return getAllItemNames(orderRepository.queryAllBy());
    }

    @Transactional(readOnly = true)
    public List<String> getOrdersWithCustomerAndOrderItemsAndItem() {
        return getAllItemNames(orderRepository.findAllBy());
    }

    private List<String> getAllItemNames(List<Order> orders) {
        return orders.stream()
                     .map(Order::getOrderItems)
                     .flatMap(Collection::stream)
                     .map(OrderItem::getItem)
                     .map(Item::getItemName)
                     .collect(Collectors.toList());
    }

}
