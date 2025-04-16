package com.angie.inventory.inventory_management.repository;

import com.angie.inventory.inventory_management.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}