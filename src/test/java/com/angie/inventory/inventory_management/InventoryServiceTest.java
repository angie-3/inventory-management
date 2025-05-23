package com.angie.inventory.inventory_management.service;

import com.angie.inventory.inventory_management.model.Item;
import com.angie.inventory.inventory_management.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private InventoryService inventoryService;

    private Item testItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testItem = new Item();
        testItem.setId(1L);
        testItem.setName("Test Item");
        testItem.setQuantity(10);
        testItem.setPrice(99.99);
    }

    @Test
    void testAddItem() {
        when(itemRepository.save(any(Item.class))).thenReturn(testItem);

        Item createdItem = inventoryService.addItem(testItem);

        assertNotNull(createdItem);
        assertEquals("Test Item", createdItem.getName());
        verify(itemRepository, times(1)).save(testItem);
    }

    @Test
    void testGetItemById() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(testItem));

        Item foundItem = inventoryService.getItemById(1L);

        assertNotNull(foundItem);
        assertEquals(1L, foundItem.getId());
        assertEquals("Test Item", foundItem.getName());
    }
}