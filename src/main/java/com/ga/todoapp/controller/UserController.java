package com.ga.todoapp.controller;

import com.ga.todoapp.model.User;
import com.ga.todoapp.model.request.LoginRequest;
import com.ga.todoapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/auth/users")
public class UserController {

    // Used to access the business logic inside UserService
    private UserService userService;

    @PostMapping("/register")
    public User createUser(@RequestBody User userObject) {
        System.out.println("Calling createUser => ");
        // Sends the User object to UserService so it checks the email, encrypts the password,
        // saves the user, and returns the saved user
        return userService.createUser(userObject);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("Calling loginUser => ");
        return userService.loginUser(loginRequest);
    }
}
