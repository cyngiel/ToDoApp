# ToDoApp

## Overview

This is a **Todo** application designed to manage tasks.

## Key Features:
- **Add Tasks**: Create new tasks by providing a title, description, and setting the task's priority (High, Medium, or Low).
- **View Tasks**: Browse through your tasks, view their details.
- **Modify and Delete Tasks**: Edit the title, description, or priority level of any existing task.
- **Request Counting**: Track the number of requests made to the API

## Technologies Used
- **Spring Boot** for the backend
- **Swagger UI** for API documentation
- **Spring Data** for database access
- **H2 Database** for in-memory storage (can be replaced with MySQL in production)
- **Lombok** for reducing boilerplate code

## API Documentation

> **🚀 Swagger UI - API Documentation**  
> You can explore and test the API using **Swagger UI**. This interactive tool provides a clear overview of all available API endpoints, allowing you to test them directly from the browser.
> To access the Swagger UI, simply visit the following URL in your browser:
> [Swagger UI - API Documentation](http://localhost:8080/swagger-ui/index.html#/)

---
## API Access

The API is available at the default port:  [http://localhost:8080](http://localhost:8080)

## API Request Counting
The API tracks the number of requests made to the `/task` endpoint for various HTTP methods such as `GET`, `POST`, `PUT`, `PATCH`, and `DELETE`. 

You can view the statistics for the counted requests at the `/logs` endpoint:

## App Info

There is an `/info` endpoint available that provides useful statistics about the current state of the application:

- **Task Count**: Displays the total number of tasks in the system.
- **Priority Breakdown**: Shows how many tasks exist for each priority level (High, Medium, Low).
- **Request Count**: Provides a count of how many requests have been made for each HTTP method (GET, POST, PUT, PATCH, DELETE) to the `/task` endpoint.

