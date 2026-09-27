package com.ga.todoapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JWTUtils {

    // Create a logger for the JWTUtils class so I can print information/errors to the console.
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    // Gets the secret key from application-dev.properties
    @Value("${jwt-secret}")
    private String jwtSecret;

    // Gets the token expiration time from application-dev.properties
    @Value("${jwt-expiration-ms}")
    private int jwtExpirationMs;

    // Create the JWT token after the user successfully logs in
    // receives the logged-in user's details in myUserDetails
    // returns a String because a JWT is ultimately a long string
    public String generateJwtToken(MyUserDetails myUserDetails) {
        // Start building a new JWT
        return Jwts.builder()
                // Who does the token belong to?
                .setSubject(myUserDetails.getUsername())
                // When was it created? new Date() means right now
                .setIssuedAt(new Date())
                // When should it expire? current time + expiration time
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                // Signs the token using the HS256 algorithm and our secret key
                // signWith = proves the token was created by our application and
                // helps detect if someone changed it.
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                // Finishes building the JWT and returns it as a String
                .compact();
    }

    // It receives a JWT token and returns a String — the username/email stored inside.
    public String getUserNameFromJwtToken(String token) {
        // Starts creating something that can read/parse a JWT.
        return Jwts.parserBuilder()
                // Uses our secret key when reading the JWT.
                // we created it using: .signWith(SignatureAlgorithm.HS256, jwtSecret)
                // So the same secret is used when checking/reading it.
                .setSigningKey(jwtSecret)
                // Finishes creating the JWT parser.
                .build()
                // Reads the JWT that the user sent.
                .parseClaimsJws(token)
                // Gets the information stored inside the JWT.
                .getBody()
                // Gets the username/email stored as the subject
                .getSubject();
    }

    // Checks whether the JWT is valid
    public boolean validateJwtToken(String authToken) {
        try {
            // Reads the token and checks it using our secret key
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(authToken);

            // If no error happens, the token is valid
            return true;

        } catch (SecurityException e) {
            // Prints an error if the JWT signature is invalid
            logger.log(
                    Level.SEVERE,
                    "Invalid JWT Signature: {0}",
                    e.getMessage()
            );
        }

        // If there was an error, the token is invalid
        return false;
    }
}
