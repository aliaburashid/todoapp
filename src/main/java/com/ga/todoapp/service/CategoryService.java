package com.ga.todoapp.service;

import com.ga.todoapp.model.Category;
import com.ga.todoapp.repository.CategoryRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ga.todoapp.exception.InformationNotFoundException;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // CREATE
    public Category createCategory(Category categoryObject) {
        System.out.println("Service Calling createCategory");
        return categoryRepository.save(categoryObject);
    }

    // READ ALL
    public List<Category> getCategories() {
        System.out.println("Service Calling getCategories");
        return categoryRepository.findAll();
    }


    // READ ONE
    public Category getOneCategory(Long id) {
        System.out.println("Service Calling getOneCategory");
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            throw new InformationNotFoundException("No category found with this id: " + id);
        }
        return category;
    }


    // UPDATE
    public Category updateCategory(Long id, Category categoryObject) {
        System.out.println("Service Calling updateCategory");
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            throw new InformationNotFoundException("No category found with this id: " + id);
        } else {
            category.setName(categoryObject.getName());
            category.setDescription(categoryObject.getDescription());

            return categoryRepository.save(category);
        }
    }


    // DELETE
    public void deleteCategory(Long id) {
        System.out.println("Service Calling deleteCategory");
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            throw new InformationNotFoundException("No category found with this id: " + id);
        } else {
            categoryRepository.deleteById(id);
        }
    }


}
