package com.ga.todoapp.repository;

import com.ga.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Tells Spring that this interface is responsible for database operations for User
public interface UserRepository extends JpaRepository<User, Long> {

    // Checks if a user already exists with the given email address
    // Returns true if the email exists, otherwise false
    boolean existsByEmailAddress(String emailAddress);

    // Finds and returns a user using their email address
    User findUserByEmailAddress(String emailAddress);
}