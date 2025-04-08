package com.angie.inventory.inventory_management.service;

import com.angie.inventory.inventory_management.controller.model.Item;
import com.angie.inventory.inventory_management.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final ItemRepository itemRepository;

    // Constructor injection for the repository
    public InventoryService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Get all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Get item by ID
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    // Add a new item
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    // Update existing item
    public Item updateItem(Long id, Item updatedItem) {
        // First, find the existing item by ID
        Optional<Item> existingItemOpt = itemRepository.findById(id);

        if (existingItemOpt.isPresent()) {
            Item existingItem = existingItemOpt.get();
            // Update fields of the existing item
            existingItem.setName(updatedItem.getName());
            existingItem.setPrice(updatedItem.getPrice());
            // Add other fields to update as needed
            return itemRepository.save(existingItem);
        } else {
            throw new RuntimeException("Item not found with id " + id); // Handle not found case
        }
    }

    // Delete an item
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}