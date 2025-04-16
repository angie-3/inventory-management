package com.angie.inventory.inventory_management.controller;

import com.angie.inventory.inventory_management.model.Item;
import com.angie.inventory.inventory_management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items") //Base URL for all inventory-related endpoints
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
    public Optional<Item> getItemById(@PathVariable Long id) {
        return inventoryService.getItemById(id);
    }

    // Post Add a new item
    @PostMapping
    public Item addItem(@RequestBody Item item){
        return inventoryService.addItem(item);
    }
    //update an item//Put
    @PutMapping("/{id}")
public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
    Item updatedItem = inventoryService.updateItem(id, item);
    if (updatedItem != null) {
        return ResponseEntity.ok(updatedItem);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    //Delete an item
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
    }
}
/*
 *Defines a rest API controller using @RestController
 *Sets up a base API endpoint(/api/items) with @RequestMapping

 *Provides CRUD operations for inventory items:

 * GET: /api/items (this retrieves all items)
 * GET: /api/items/{id} (retrieves a specific item)
 * POST: /api/items (Add a new item)
 * PUT: /api/item/{id}(Update an existing item)
 * DELETE: /api/items/id{id} (Delete an item)
 */