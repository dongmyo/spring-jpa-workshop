package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

// NOTE #6: Item Entity를 위한 Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
