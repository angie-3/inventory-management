package com.angie.inventory.inventory_management.service;

import com.angie.inventory.inventory_management.model.Item;
import com.angie.inventory.inventory_management.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final ItemRepository itemRepository;

    //constructor injection for the repository
    public InventoryService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;

    }

    //Get all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    //Get item by ID
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    //add a new item
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    //update existing item
    public Item updateItem(Long id, Item updatedItem) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(updatedItem.getName());
                    item.setQuantity(updatedItem.getQuantity());
                    item.setPrice(updatedItem.getPrice());
                    return itemRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    //delete an item
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }


}