package com.ga.todoapp.repository;

import com.ga.todoapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// Category -> What type of object are we storing?
// Long     -> What type is Category's id?
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
