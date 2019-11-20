package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
