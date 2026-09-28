package com.ga.todoapp.repository;

import com.ga.todoapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Category -> What type of object are we storing?
// Long     -> What type is Category's id?
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // CREATE - check if the current user already has a category with this name
    Category findByNameAndUserId(String categoryName, Long userId);

    // GET ALL - get all categories that belong to the current user
    List<Category> findByUserId(Long userId);

    // GET ONE, UPDATE, DELETE - find category by category id and current user id
    Category findByIdAndUserId(Long categoryId, Long userId);

}
