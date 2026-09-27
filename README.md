# Todo App

A REST API Todo application built using Spring Boot, Spring Data JPA, PostgreSQL, and Spring Security with JWT authentication.

## GitHub Repository

https://github.com/aliaburashid/todoapp

## Project Overview

This project was developed step by step as part of the Todo App Lab.

The application includes:
- A Spring Boot application using Maven.
- A development specific Spring Profile.
- A PostgreSQL database connection.
- A Category model with a name and description.
- An Item model with a name, description, and due date.
- A User model with a username, email address, and password.
- A UserProfile model with a first name, last name, and profile description.
- A one-to-one relationship between User and UserProfile.
- A one-to-many relationship between Category and Item.
- Full CRUD operations for categories and items.
- User registration and login.
- Password encryption using BCrypt.
- JWT authentication using Spring Security.
- Public registration and login endpoints.
- Protected Category and Item endpoints that require authentication.
- REST API endpoints tested using Postman.

## Design Decisions

### Layered Architecture

I separated the application into Model, Repository, Service, and Controller layers.

### PostgreSQL

I used PostgreSQL to store the application's data.

### Spring Data JPA

I used Spring Data JPA to communicate with the PostgreSQL database without having to manually write SQL queries for the basic CRUD operations.

### Category and Item Relationship

I created a one-to-many relationship where one Category can have multiple Items, while each Item belongs to one Category.

### User and UserProfile Relationship

I created a one-to-one relationship between User and UserProfile. The User stores the username, email address, and password, while UserProfile stores additional information such as the first name, last name, and profile description.

### Development Profile

I created a development specific Spring Profile to keep the development configuration separate.

### Spring Security and JWT

I used Spring Security with JSON Web Tokens (JWT) for authentication. Users can register and login through public endpoints. After a successful login, a JWT is returned and used as a Bearer Token to access the protected Category and Item endpoints.

### Password Security

I used BCrypt to encrypt user passwords before they are stored in the database.

## Reasons Behind My Decisions

- **Layered architecture:** keeps different responsibilities of the application separated and makes the code easier to understand and maintain.
- **PostgreSQL:** provides persistent storage for users, categories, and todo items.
- **Spring Data JPA:** provides built-in methods such as `save()`, `findAll()`, `findById()`, and `deleteById()`.
- **Category and Item relationship:** allows todo items to be organised under their related categories.
- **User and UserProfile relationship:** separates the user's login information from their profile information.
- **Development profile:** allows development specific settings, such as the local database configuration, to be kept together.
- **Spring Security:** protects the API endpoints from unauthenticated requests.
- **JWT authentication:** allows the REST API to authenticate requests without storing user login sessions.
- **BCrypt:** prevents passwords from being stored as plain text in the database.

## What Went Right

- The Spring Boot application was successfully created and tested with a `/hello` endpoint.
- The development profile was successfully configured.
- The application connected successfully to PostgreSQL.
- Hibernate created the tables from the models.
- Full CRUD operations were created for Category and Item.
- The Category and Item relationship worked successfully.
- Items can only be retrieved, updated, or deleted when they belong to the specified category.
- User registration and login were successfully implemented.
- Passwords are encrypted using BCrypt before being stored.
- Login successfully generates a JWT.
- The JWT can be passed as a Bearer Token to access protected endpoints.
- Requests to protected endpoints without a JWT are blocked.
- Authenticated GET and POST requests were successfully tested in Postman.

## Challenges I Faced

One challenge was understanding how the Controller, Service, and Repository work together. Building each layer step by step helped me understand the flow from an API request to the database.

Another challenge was understanding the relationship between Category and Item. I learned how to map the two models and how to retrieve items using their category ID.

I also had to make sure my PostgreSQL configuration was correct so that Spring Boot could connect to the database successfully.

Another challenge was implementing JWT authentication. I had to understand how the JWT filter, JWT utility class, Spring Security configuration, and user details service work together.

While testing JWT authentication, I encountered an issue where the JWT utility was not being injected into the JWT filter. I fixed this by allowing Spring to manage the JWT filter instead of manually creating it.

I also encountered an issue when validating the JWT because the token contained claims but was being parsed as a plaintext JWT. Changing the validation to parse the token as a claims JWS allowed the JWT to be successfully validated.

Debugging these issues helped me better understand how Spring manages components and how a JWT is validated before a protected endpoint can be accessed.

## My Favourite Part

My favourite part was testing the API in Postman and seeing the application develop from basic CRUD operations into a secured REST API. I especially liked testing the JWT authentication and seeing a protected endpoint successfully return data when a valid Bearer Token was provided, while requests without authentication were blocked.