package com.ga.todoapp.repository;

import com.ga.todoapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Find an Item where its name matches this value.
    Item findByName(String itemName);
    // Look at the Item's category, then its id, and return all matching Items.
    List<Item> findByCategoryId(Long categoryId);
    // Item actually belongs to that Category
    Item findByIdAndCategoryId(Long itemId, Long categoryId);
}
