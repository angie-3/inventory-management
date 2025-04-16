package com.angie.inventory.inventory_management;

import com.angie.inventory.inventory_management.model.Item;
import com.angie.inventory.inventory_management.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class InventoryManagementApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    private Item testItem;

    @BeforeEach
    void setUp() {
        // Prepare a test item before each test
        testItem = new Item();
        testItem.setName("Test Item");
        testItem.setQuantity(10);
        testItem.setPrice(99.99);
    }

    @Test
    void testCreateItem() {
        // Save the item
        Item savedItem = itemRepository.save(testItem);

        // Assertions
        assertNotNull(savedItem.getId(), "Item ID should not be null");
        assertEquals("Test Item", savedItem.getName(), "Item name should match");
        assertEquals(10, savedItem.getQuantity(), "Item quantity should match");
        assertEquals(99.99, savedItem.getPrice(), "Item price should match");
    }

    @Test
    void testFindItem() {
        // Save the item
        Item savedItem = itemRepository.save(testItem);

        // Find the item by ID
        Item foundItem = itemRepository.findById(savedItem.getId()).orElse(null);
        
        // Assertions
        assertNotNull(foundItem, "Item should be found");
        assertEquals(savedItem.getName(), foundItem.getName(), "Item names should match");
    }

    @Test
    void testDeleteItem() {
        // Save the item
        Item savedItem = itemRepository.save(testItem);

        // Delete the item
        itemRepository.delete(savedItem);

        // Try to find the item after deletion
        Item foundItem = itemRepository.findById(savedItem.getId()).orElse(null);
        assertNull(foundItem, "Item should be deleted and not found");
    }

    @Test
    void testUpdateItem() {
        // Save the item
        Item savedItem = itemRepository.save(testItem);

        // Update the item
        savedItem.setName("Updated Item");
        savedItem.setQuantity(20);
        savedItem.setPrice(75.00);
        Item updatedItem = itemRepository.save(savedItem);

        // Assertions
        assertEquals("Updated Item", updatedItem.getName(), "Item name should be updated");
        assertEquals(20, updatedItem.getQuantity(), "Item quantity should be updated");
        assertEquals(75.00, updatedItem.getPrice(), "Item price should be updated");
    }
}