package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.dto.OrderIdGetter;
import com.nhn.workshop.jpa.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<OrderIdGetter> findAllBy(Pageable pageable);

    @Query("select o from Order as o"
           + " inner join fetch o.customer as c"
           + " left join fetch o.orderItems as oi"
           + " left join fetch oi.item as i"
           + " where o.orderId in ?1")
    List<Order> getOrderWithAssociations(Collection<Long> orderIds);

//    @Query(value = "select o from Order as o"
//                   + " inner join fetch o.customer as c"
//                   + " left join fetch o.orderItems as oi"
//                   + " left join fetch oi.item as i",
//            countQuery = "select count(o) from Order as o")
//    Page<Order> getPagedOrderWithAssociations(Pageable pageable);

}
