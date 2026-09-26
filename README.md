# Todo App

A REST API Todo application built using Spring Boot, Spring Data JPA, and PostgreSQL.

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
- A one-to-many relationship between Category and Item.
- Full CRUD operations for categories and items.
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

### Development Profile
I created a development specific Spring Profile to keep the development configuration separate.

## Reasons Behind My Decisions

- **Layered architecture:** keeps different responsibilities of the application separated and makes the code easier to understand and maintain.
- **PostgreSQL:** provides persistent storage for categories and todo items.
- **Spring Data JPA:** provides built-in methods such as `save()`, `findAll()`, `findById()`, and `deleteById()`.
- **Category and Item relationship:** allows todo items to be organised under their related categories.
- **Development profile:** allows development specific settings, such as the local database configuration, to be kept together.

## What Went Right

- The Spring Boot application was successfully created and tested with a `/hello` endpoint.
- The development profile was successfully configured.
- The application connected successfully to PostgreSQL.
- Hibernate created the tables from the models.
- Full CRUD operations were created for Category and Item.
- The Category and Item relationship worked successfully.
- The REST API endpoints were successfully tested using Postman.
- Items can only be retrieved, updated, or deleted when they belong to the specified category.

## Challenges I Faced

One challenge was understanding how the Controller, Service, and Repository work together. Building each layer step by step helped me understand the flow from an API request to the database.

Another challenge was understanding the relationship between Category and Item. I learned how to map the two models and how to retrieve items using their category ID.

I also had to make sure my PostgreSQL configuration was correct so that Spring Boot could connect to the database successfully.

## My Favourite Part

My favourite part was testing the CRUD endpoints in Postman and seeing the changes reflected in the database. I especially liked creating Items under different Categories and seeing how the relationship between the two tables worked.