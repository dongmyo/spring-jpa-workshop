package com.nhn.workshop.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "OrderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_line_id")
    private Long orderLineId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // NOTE #4: OrderItem-Item 연관관계 설정
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Long quantity;

}
