package com.ga.todoapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    // Gets the JWT from the Authorization header
    private String parseJwt(HttpServletRequest request) {

        // Gets the Authorization header
        String headerAuth = request.getHeader("Authorization");

        // Checks that the header exists and starts with "Bearer "
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {

            // Removes "Bearer " and returns only the JWT
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        try {

            // Gets the JWT from the request
            String jwt = parseJwt(request);
            System.out.println("jwt: ==> " + jwt);

            // Checks that the JWT exists and is valid
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

                // Gets the email stored inside the JWT
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                System.out.println("username: ==> " + username);

                // Finds the user using their email
                UserDetails userDetails =
                        this.myUserDetailsService.loadUserByUsername(username);

                System.out.println(
                        "userDetails: ==> " + userDetails.getUsername()
                );

                // Creates an authenticated Spring Security object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // Tells Spring Security this request belongs to an authenticated user
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }

        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        // Continues the request to the controller
        filterChain.doFilter(request, response);
    }
}