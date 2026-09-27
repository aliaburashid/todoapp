package com.ga.todoapp.service;

import com.ga.todoapp.exception.InformationExistException;
import com.ga.todoapp.model.User;
import com.ga.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

// Tells Spring that this class contains the business logic for User
@Service
public class UserService {
    // Used to communicate with the users table in the database
    private final UserRepository userRepository;

    // Used to encrypt/hash the users password before saving it
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // Creates/registers a new user
    public User createUser(User userObject) {

        System.out.println("Service Calling createUser =>");

        // Check the database to see if this email address already exists
        // if the email does NOT already exist
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            // Get the password the user entered
            // Encrypt/hash it using BCrypt
            // Then replace the original password with the encrypted password
            userObject.setPassword(
                    passwordEncoder.encode(userObject.getPassword())
            );

            // Save the new user into the users table
            // Because User -> UserProfile uses CascadeType.
            // ALL, the user's profile will also be saved
            return userRepository.save(userObject);

        } else {

            // If the email already exists, do not create another user
            // Instead, throw an exception
            throw new InformationExistException(
                    "User with email address "
                            + userObject.getEmailAddress()
                            + " already exists."
            );
        }
    }
}

