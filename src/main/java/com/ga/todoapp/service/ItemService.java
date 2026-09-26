package com.ga.todoapp.service;

import com.ga.todoapp.exception.InformationNotFoundException;
import com.ga.todoapp.model.Category;
import com.ga.todoapp.model.Item;
import com.ga.todoapp.repository.CategoryRepository;
import com.ga.todoapp.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Item createItem(Long categoryId, Item itemObject) {
        System.out.println("Service Calling createItem => ");

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "Category with this id: " + categoryId + " not found"
                        ));

        itemObject.setCategory(category);
        return itemRepository.save(itemObject);
    }


    public List<Item> getItems(Long categoryId) {
        System.out.println("Service Calling getItems => ");

        categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "Category with this id: " + categoryId + " not found"
                        ));

        return itemRepository.findByCategoryId(categoryId);
    }


    public Item getItem(Long categoryId, Long itemId) {
        System.out.println("Service Calling getItem => ");

        // Check if the category exists
        categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "Category with this id: " + categoryId + " not found"
                        ));

        // Find the item only if it belongs to this category
        Item item = itemRepository.findByIdAndCategoryId(itemId, categoryId);

        // Throw an error if the item does not belong to the category
        if (item == null) {
            throw new InformationNotFoundException(
                    "Item with id: " + itemId +
                            " was not found in category: " + categoryId
            );
        }

        return item;
    }


    public Item updateItem(Long categoryId, Long itemId, Item itemObject) {
        System.out.println("Service calling updateItem => ");

        // Check if the category exists
        categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "Category with this id: " + categoryId + " not found"
                        ));

        // Find the item only if it belongs to this category
        Item item = itemRepository.findByIdAndCategoryId(itemId, categoryId);

        // Throw an error if the item does not belong to the category
        if (item == null) {
            throw new InformationNotFoundException(
                    "Item with id: " + itemId +
                            " was not found in category: " + categoryId
            );
        }

        // Update the items information
        item.setName(itemObject.getName());
        item.setDescription(itemObject.getDescription());
        item.setDueDate(itemObject.getDueDate());

        return itemRepository.save(item);
    }


    public void deleteItem(Long categoryId, Long itemId) {
        System.out.println("Service calling deleteItem => ");

        // Check if the category exists
        categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "Category with this id: " + categoryId + " not found"
                        ));

        // Check if the item exists AND belongs to this category
        Item item = itemRepository.findByIdAndCategoryId(itemId, categoryId);

        if (item == null) {
            throw new InformationNotFoundException(
                    "Item with this id: " + itemId + " not found in category: " + categoryId
            );
        }

        // Delete the item
        itemRepository.deleteById(itemId);
    }

}
