package com.ga.todoapp.service;

import com.ga.todoapp.model.Category;
import com.ga.todoapp.model.User;
import com.ga.todoapp.repository.CategoryRepository;
import com.ga.todoapp.security.MyUserDetails;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.ga.todoapp.exception.InformationNotFoundException;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public static User getCurrentLoggedInUser() {
        // ( SecurityContextHolder ): spring security keeps info and the currently authenticated/logged-in user here
        // (.getContext() ) : Gets the current security information.
        // (.getAuthentication() ) : info about who logged in
        // ( .getPrincipal() ): Gets the actual logged-in user's details.
        MyUserDetails userDetails =  (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // MyUserDetails contains the actual User object.
        return userDetails.getUser();
    }

    // CREATE
    public Category createCategory(Category categoryObject) {
        System.out.println("Service Calling createCategory");

        // get the currently logged-in user
        User currentUser = getCurrentLoggedInUser();

        // connect the category to the currently logged-in user
        categoryObject.setUser(currentUser);

        return categoryRepository.save(categoryObject);
    }

    // READ ALL
    public List<Category> getCategories() {
        System.out.println("Service Calling getCategories");

        // get the currently logged-in user
        User currentUser = getCurrentLoggedInUser();

        return categoryRepository.findByUserId(currentUser.getId());
    }


    // READ ONE
    public Category getOneCategory(Long id) {
        System.out.println("Service Calling getOneCategory");

        // get the currently logged-in user
        User currentUser = getCurrentLoggedInUser();

        Category category = categoryRepository.findByIdAndUserId(id, currentUser.getId());

        if (category == null) {
            throw new InformationNotFoundException("No category found with this id: " + id);
        }
        return category;
    }


    // UPDATE
    public Category updateCategory(Long id, Category categoryObject) {
        System.out.println("Service Calling updateCategory");

        // get the currently logged-in user
        User currentUser = getCurrentLoggedInUser();

        Category category = categoryRepository.findByIdAndUserId(id, currentUser.getId());

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

        // get the currently logged-in user
        User currentUser = getCurrentLoggedInUser();

        Category category = categoryRepository.findByIdAndUserId(id, currentUser.getId());

        if (category == null) {
            throw new InformationNotFoundException("No category found with this id: " + id);
        } else {
            categoryRepository.deleteById(id);
        }
    }


}
