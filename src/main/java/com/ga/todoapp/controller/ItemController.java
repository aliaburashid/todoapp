package com.ga.todoapp.controller;

import com.ga.todoapp.model.Item;
import com.ga.todoapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // C - Create = HTTP POST - Create an item under a category
    @PostMapping("/categories/{categoryId}/items")
    public Item createItem (@PathVariable(value = "categoryId") Long categoryId, @RequestBody Item itemObject) {
        System.out.println("Calling createItem => ");
        return itemService.createItem(categoryId, itemObject);
    }

    // R - Read = HTTP GET - Get all items under a category
    @GetMapping("/categories/{categoryId}/items")
    public List<Item> getItems(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("Calling getItems => ");
        return itemService.getItems(categoryId);
    }

    // R - Read = HTTP GET - Get one item
    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Item getItem( @PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "itemId") Long itemId) {
        System.out.println("Calling getItem => ");
        return itemService.getItem(categoryId, itemId);
    }

    // U - Update = HTTP PUT - Update an item
    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public Item updateItem(
            @PathVariable(value = "categoryId") Long categoryId,
            @PathVariable(value = "itemId") Long itemId,
            @RequestBody Item itemObject) {

        System.out.println("Calling updateItem => ");
        return itemService.updateItem(categoryId, itemId, itemObject);
    }

    // D - Delete = HTTP DELETE - Delete an item
    @DeleteMapping("/categories/{categoryId}/items/{itemId}")
    public void deleteItem(
            @PathVariable(value = "categoryId") Long categoryId,
            @PathVariable(value = "itemId") Long itemId) {

        System.out.println("Calling deleteItem => ");
        itemService.deleteItem(categoryId, itemId);
    }

}
