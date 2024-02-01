# BackendExam

## Overview

Welcome to the BackendExam project, this was a collaboration with a fellow student. This project has been designed as a ordersystem for a factoryt that sells machines, the application manages the necessary entities such as Addresses, Customers, Machines, Orders, PartEntities, and Subassemblies. It's also utilizing Spring Boot, the project facilitates CRUD operations through its RESTful API endpoints.

## Project Status

It encompasses the required functionalities and meets the specified objectives. this was an exam for the third semester and achieved an A.

## API Endpoints

Below are the API endpoints available in this project, along with examples of how to use them:

### Address
- **Endpoint:** `http://127.0.0.1:8080/api/addresses`
- **Example POST Request:**
  ```json
  {
    "street": "teststreet",
    "streetNumber": 123,
    "city": "testcity",
    "country": "testcountry"
  }
  ```

  ### Customer
- **Endpoint:** `http://127.0.0.1:8080/api/customers`
- **Example POST Request:**
  ```json
  {
    "street": "testname",
    "email": "test@mail.com",
    "dob": "2003-08-12",
    "age": 21,
    "addresses": [],
    "orders": []
  }
  ```
### Machine
- **Endpoint:** `http://127.0.0.1:8080/api/machines`
- **Example POST Request:**
  ```json
  {
  "name": "computer",
  "subassemblies": []
  }
  ```
### Order
- **Endpoint:** `http://127.0.0.1:8080/api/orders`
- **Example POST Request:**
  ```json
  {
  "deliveryAddress": {},
  "machines": []
  }
  ```
### PartEntities
- **Endpoint:** `http://127.0.0.1:8080/api/part-entities`
- **Example POST Request:**
  ```json
  {
  "name": "partentity test"
  }
  ```
  
### Subassembly
- **Endpoint:** `http://127.0.0.1:8080/api/subassemblies`
- **Example POST Request:**
  ```json
  {
  "name": "subessembly test",
  "parts": []
  }
  ```

## Dependencies

This project utilizes a variety of dependencies for optimal functionality and efficiency, such as Lombok. Here's an overview of these dependencies and how we hacve integrated it in the project (mostly for our own internal reference):

- **Spring Boot Starter Web:** A core dependency for building the web application. It is used to create RESTful APIs with Spring MVC, offering a robust framework for handling web requests.
- **Spring Boot Starter Data JPA:** This dependency is essential for ORM (Object-Relational Mapping) and database operations. It leverages the Java Persistence API to streamline database interactions.
- **H2 Database:** An in-memory database that aids in testing database interactions without the need for a separate DB setup. It's been a pleasant surprise in terms of simplicity.
- **Lombok:** A handy tool that reduces standard boilerplate code. It's used for auto-generating getters, setters, and constructors (such as `@NoArgsConstructor`), significantly reducing the time and code needed to write fundamental components of the code.
- **Spring Boot Starter Test:** This dependency is utilized primarily for testing purposes, including both unit and integration tests. It provides a comprehensive testing framework compatible with Spring Boot applications.
- **RestAssured MockMVC:** An important tool for testing the web layer. It facilitates the simulation of HTTP requests and responses, making it easier to test API endpoints.

All these dependencies are managed via Maven, which, despite occasional hiccups, generally provides an "efficient" means for project builds and dependency management.
