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
        testItem = new Item();
        testItem.setName("Test Item");
        testItem.setQuantity(10);
        testItem.setPrice(99.99);
    }

    @Test
    void testCreateItem() {
        Item savedItem = itemRepository.save(testItem);

        assertNotNull(savedItem.getId(), "Item ID should not be null");
        assertEquals("Test Item", savedItem.getName());
        assertEquals(10, savedItem.getQuantity());
        assertEquals(99.99, savedItem.getPrice());
    }

    @Test
    void testFindItem() {
        Item savedItem = itemRepository.save(testItem);
        Item foundItem = itemRepository.findById(savedItem.getId()).orElse(null);

        assertNotNull(foundItem, "Item should be found");
        assertEquals(savedItem.getName(), foundItem.getName());
    }

    @Test
    void testDeleteItem() {
        Item savedItem = itemRepository.save(testItem);
        itemRepository.delete(savedItem);

        Item foundItem = itemRepository.findById(savedItem.getId()).orElse(null);
        assertNull(foundItem, "Item should be deleted and not found");
    }

    @Test
    void testUpdateItem() {
        Item savedItem = itemRepository.save(testItem);
        savedItem.setName("Updated Item");
        savedItem.setQuantity(20);
        savedItem.setPrice(75.00);

        Item updatedItem = itemRepository.save(savedItem);

        assertEquals("Updated Item", updatedItem.getName());
        assertEquals(20, updatedItem.getQuantity());
        assertEquals(75.00, updatedItem.getPrice());
    }
}