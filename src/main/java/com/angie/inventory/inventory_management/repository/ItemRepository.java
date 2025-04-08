package com.angie.inventory.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angie.inventory.inventory_management.controller.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
/* 
* @Repository: marks this interface as a Spring Data repository.
* JpaRepository<Item, Long>: provides built-in CRUD operations for
Item entities using Long as the Id type.
*/