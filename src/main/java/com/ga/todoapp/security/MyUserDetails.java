package com.ga.todoapp.security;

import com.ga.todoapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor

// Connects our User model with Spring Security
// UserDetails tells Spring Security what information it needs about a user
public class MyUserDetails implements UserDetails {

    // Stores the User object from our database
    @Getter
    private User user;

    // Returns the users roles/permissions
    // Currently there are no roles, so an empty HashSet is returned
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    // Gives Spring Security the users encrypted password
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // Gives Spring Security the value used to identify the user
    // In our application, the email address is used as the username
    @Override
    public String getUsername() {
        return user.getEmailAddress();
    }

    // The following methods tell Spring that the account is valid and active

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}