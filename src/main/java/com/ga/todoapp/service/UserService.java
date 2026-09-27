package com.ga.todoapp.service;

import com.ga.todoapp.exception.InformationExistException;
import com.ga.todoapp.model.User;
import com.ga.todoapp.model.request.LoginRequest;
import com.ga.todoapp.model.response.LoginResponse;
import com.ga.todoapp.repository.UserRepository;
import com.ga.todoapp.security.JWTUtils;
import com.ga.todoapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils,
                       @Lazy AuthenticationManager authenticationManager,
                       @Lazy MyUserDetails myUserDetails) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
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
            throw new InformationExistException("User with email address " + userObject.getEmailAddress() + " already exists.");
        }
    }


    // Finds a user in the database using their email address
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }


    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {

            // Checks if the email and password are correct
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // Stores the authenticated user in Spring Security
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Gets our logged-in user
            myUserDetails = (MyUserDetails) authentication.getPrincipal();

            // Generates a JWT for the logged-in user
            final String JWT = jwtUtils.generateJwtToken(myUserDetails);

            // Returns the JWT
            return ResponseEntity.ok(new LoginResponse(JWT));

        } catch (Exception e) {

            // Returned if the login details are incorrect
            return ResponseEntity.ok(new LoginResponse("Error: username or email is incorrect." + e));
        }
    }
}

