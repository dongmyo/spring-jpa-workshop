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
    // NOTE #1: Pagination 쿼리로 ID만 가져온다
    Page<OrderIdGetter> findAllBy(Pageable pageable);

    // NOTE #2. ID 조건에 해당하는 Entity들에만 Fetch JOIN 적용
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
