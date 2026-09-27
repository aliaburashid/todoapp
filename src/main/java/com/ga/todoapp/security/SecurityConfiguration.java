package com.ga.todoapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Tells Spring that this class contains security configuration
@Configuration
public class SecurityConfiguration {

    // Creates the PasswordEncoder object that Spring can use
    // We use this inside UserService to encrypt the users password
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Bcrypt securely hashes the password before it is stored in the database
        return new BCryptPasswordEncoder();
    }


    // Defines which URLs can be accessed and which URLs should be blocked
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // Disables CSRF protection, Common for stateless REST APIs
                .csrf(csrf -> csrf.disable())

                // Makes the application stateless
                // Spring Security will not store user login sessions
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Defines which endpoints are public and which require authentication
                .authorizeHttpRequests(auth -> auth

                        // These endpoints can be accessed without logging in
                        .requestMatchers("/auth/users/register",
                                "/error").permitAll()

                        // Every other endpoint requires the user to be authenticated
                        .anyRequest().authenticated()
                );


        // Builds and returns the security configuration
        return http.build();
    }

}
