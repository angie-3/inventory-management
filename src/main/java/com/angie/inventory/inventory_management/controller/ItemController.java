package com.angie.inventory.inventory_management.controller;

import com.angie.inventory.inventory_management.model.Item;
import com.angie.inventory.inventory_management.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:3001")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    // GET all items
    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // POST a new item
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }
}
