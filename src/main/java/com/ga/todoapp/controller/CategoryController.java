package com.ga.todoapp.controller;

import com.ga.todoapp.model.Category;
import com.ga.todoapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }


    // C - Create = HTTP POST - Create a category
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("Calling createCategory");
        return categoryService.createCategory(categoryObject);
    }


    // R - Read = HTTP GET - Get all categories
    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("Calling getCategories");
        return categoryService.getCategories();
    }


    // R - Read = HTTP GET - Get one category by id
    @GetMapping("/categories/{id}")
    public Category getOneCategory(@PathVariable Long id) {
        System.out.println("Calling getOneCategory");
        return categoryService.getOneCategory(id);
    }


    // U - Update = HTTP PUT - Update a category
    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryObject) {
        System.out.println("Calling updateCategory");
        return categoryService.updateCategory(id, categoryObject);
    }


    // D - Delete = HTTP DELETE - Delete a category
    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        System.out.println("Calling deleteCategory");
        categoryService.deleteCategory(id);
    }
}
