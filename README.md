# Todo App

A REST API Todo application built using Spring Boot, Spring Data JPA, and PostgreSQL.

## GitHub Repository

https://github.com/aliaburashid/todoapp

## Project Overview

This project is being developed step by step as part of the Todo App Lab.

So far, the application includes:
- A Spring Boot application using Maven.
- A development specific Spring Profile.
- A PostgreSQL database connection.
- A Category model with a name and description.
- Full CRUD operations for categories.
- REST API endpoints tested using Postman.

## Design Decisions

### Layered Architecture
I separated the application into Model, Repository, Service, and Controller layers.

### PostgreSQL
I used PostgreSQL to store the application's data.

### Spring Data JPA
I used Spring Data JPA to communicate with the PostgreSQL database without having to manually write SQL queries for the basic CRUD operations.

### Development Profile
I created a development specific Spring Profile to keep the development configuration separate.

## Reasons Behind My Decisions

- **Layered architecture:** keeps different responsibilities of the application separated and makes the code easier to understand and maintain.
- **PostgreSQL:** provides persistent storage for categories and later todo items.
- **Spring Data JPA:** provides built-in methods such as `save()`, `findAll()`, `findById()`, and `deleteById()`.
- **Development profile:** allows development specific settings, such as the local database configuration, to be kept together.

## What Went Right

- The Spring Boot application was successfully created and tested with a `/hello` endpoint.
- The development profile was successfully configured.
- The application connected successfully to PostgreSQL.
- Hibernate created the Category table from the Category model.
- All five Category CRUD operations were successfully tested using Postman.

## Challenges I Faced

One challenge was understanding how the Controller, Service, and Repository work together. Building each layer step by step helped me understand the flow from an API request to the database.

I also had to make sure my PostgreSQL configuration was correct so that Spring Boot could connect to the database successfully.

## My Favourite Part

My favourite part so far was testing the CRUD endpoints in Postman and seeing the changes reflected in the database. It helped me understand how the different parts of the application connect together.