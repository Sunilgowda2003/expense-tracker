# Expense Tracker REST API

A simple Expense Tracker REST API built using Java and Spring Boot. This application allows users to manage expenses in memory without using a database.

## Features

- Add a new expense
- View all expenses
- Filter expenses by category
- Calculate total expenses
- Calculate total expenses by category
- Delete an expense
- Input validation
- Global exception handling
- Swagger API documentation

## Technologies Used

- Java 25
- Spring Boot 4.1.0
- Maven
- Spring Web
- Spring Validation
- SpringDoc OpenAPI (Swagger)

## Project Structure

```
src
 ├── controller
 ├── exception
 ├── model
 ├── service
 └── resources
```

## API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /expenses | Add expense |
| GET | /expenses | View all expenses |
| GET | /expenses/category/{category} | Filter by category |
| GET | /expenses/total | Total expenses |
| GET | /expenses/total/{category} | Total by category |
| DELETE | /expenses/{id} | Delete expense |

## Run the Project

```bash
mvn spring-boot:run
```

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```
## Repository

https://github.com/Sunilgowda2003/expense-tracker
## Author

Sunil J S
