package com.ga.todoapp.security;

// Now Spring Security knows what a user looks like through MyUserDetails, but it still needs a way to:
// Take an email address → search PostgreSQL → find that User → turn it into MyUserDetails.

import com.ga.todoapp.model.User;
import com.ga.todoapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

// Connects our UserService to Spring Security
// UserDetailsService tells Spring Security how to find a user
public class MyUserDetailsService implements UserDetailsService {

    // Used to find the user from our database
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Finds the user in the database using their email address
        User user = userService.findUserByEmailAddress(email);
        // Converts our User into MyUserDetails so Spring Security can use it
        return new MyUserDetails(user);
    }
}
