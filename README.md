# Bank Account Service

A Spring Boot microservice for managing bank accounts and customers, built with Java 17.

## Technologies

* Java 17
* Spring Boot 4
* Spring Data JPA
* Hibernate
* GraphQL
* Spring Data REST
* SpringDoc OpenAPI
* Swagger UI
* H2 Database
* Maven
* Lombok

## Features

* Bank account management
* Customer management
* REST API
* GraphQL API
* Spring Data JPA persistence
* H2 in-memory database
* Swagger/OpenAPI documentation
* DTO and Entity mapping

## Architecture

```text
src/main/java
└── org.sid.bank_account_service
    ├── controllers
    ├── dtos
    ├── entities
    ├── mappers
    ├── repositories
    └── services
```

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

DTOs and mappers are used to separate the API data from the persistence entities.

## GraphQL

The application provides a GraphQL API for querying and modifying bank accounts.

Example query:

```graphql
{
    bankAccounts {
        id
        balance
        type
    }
}
```

Example mutation:

```graphql
mutation {
    addBankAccount(
        request: {
            balance: 5000
            currency: "MAD"
            type: "CURRENT"
        }
    ) {
        id
        balance
        currency
        type
    }
}
```

## REST API

The application also supports REST APIs for bank account management.

Typical operations include:

```text
GET    /bankAccounts
GET    /bankAccounts/{id}
POST   /bankAccounts
PUT    /bankAccounts/{id}
DELETE /bankAccounts/{id}
```

## Swagger

Swagger UI is available when the application is running:

```text
http://localhost:8081/swagger-ui/index.html
```

The OpenAPI specification is available at:

```text
http://localhost:8081/v3/api-docs
```

## H2 Database

The project uses an H2 in-memory database for development.

H2 Console:

```text
http://localhost:8081/h2-console
```

## Installation

Clone the repository:

```bash
git clone https://github.com/HajarBoulmane/bank-account-service.git
cd bank-account-service
```

Build the project:

```bash
./mvnw clean install
```

Run the application:

```bash
./mvnw spring-boot:run
```

The application runs by default on:

```text
http://localhost:8081
```

## Project Status

This project is part of a learning project focused on Spring Boot, REST APIs, GraphQL, JPA, and microservices architecture.
