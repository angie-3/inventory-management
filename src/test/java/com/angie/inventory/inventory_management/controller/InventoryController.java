package com.angie.inventory.inventory_management.controller;

import com.angie.inventory.inventory_management.model.Item;
import com.angie.inventory.inventory_management.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items") //Base URL for all endpoints
public class InventoryController {
    private final InventoryService inventoryService;

    //Injecting Inventory service through constructors
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Get all items
    @GetMapping
    public List<Item> getAllItems() {
        return inventoryService.getAllItems();
    }

    // Get item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Optional<Item> item = inventoryService.getItemById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a new item
    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        Item savedItem = inventoryService.addItem(item);
        return ResponseEntity.ok(savedItem);
    }
    //update an item
    @PutMapping("/{id}")
    public ResponseEntity<Item> updatedItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        try {
            Item item = inventoryService.updateItem(id, updatedItem);
            return ResponseEntity.ok(item);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    //Delete an item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
/*
 *Defines API endpoints under /api/items
 *Uses @RestController to expose RESTfuk API endpoints
 *Uses ResponseEntity<> to return appropiate HTTP status codes
 *Handles:
 * GET -> /api/items (Get all items)
 * GET -> /api/items/{id} (Get a specific item)
 * POST -> /api/items (Add a new item)
 * PUT -> /api/item/{id}(Update an existing item)
 * DELETE -> /api/items/id{id} (Delete an item)
 */