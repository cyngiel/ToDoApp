# ToDoApp



This is a **Todo** application designed to manage tasks.

## Key Features:
- **Add Tasks**: Create new tasks by providing a title, description, and setting the task's priority (High, Medium, or Low).
- **View Tasks**: Browse through your tasks, view their details.
- **Modify and Delete Tasks**: Edit the title, description, or priority level of any existing task.
- **Request Counting**: Track the number of requests made to the API

## ⚙️Technologies Used
- **Spring Boot** for the backend
- **Swagger UI** for API documentation
- **Spring Data** for database access
- **Spring Security** for security
- **JSON Web Token** for authentication
- **H2 Database** for in-memory storage (can be replaced with MySQL in production)
- **Lombok** for reducing boilerplate code

---

## 📄 API Documentation

> **🚀 Swagger UI - API Documentation**  
> You can explore and test the API using **Swagger UI**. This interactive tool provides a clear overview of all available API endpoints, allowing you to test them directly from the browser.
> To access the Swagger UI, simply visit the following URL in your browser:
> [Swagger UI - API Documentation](http://localhost:8080/swagger-ui/index.html#/)

---
## 🌐 API Access

The API is available at the default port:  [http://localhost:8080](http://localhost:8080)
and secured using a JWT token, ensuring that only authenticated users with a valid token can access protected endpoints.

#### **Sign Up**
To create a new account, send a `POST` request to the `/signup` endpoint with the necessary user details.

#### **Login**
To Sign in and obtain a token for accessing protected resources, send a `POST` request to the `/login` endpoint with your login credentials.

---

## 🔑 Test User (For Testing Purposes)

For convenience, a **test user** is pre-configured so you can start testing without creating a new account.

**Test User Credentials:**
- **Username**: `test`
- **Password**: `test`

Log in using these credentials at the `/login` endpoint to receive a JWT token for testing.

---

## 🔒 Authentication Required Endpoints

The following endpoints require authentication. To access these, you must provide a valid authentication token in the request header:

### **Protected Endpoints**:
- `/tasks/**`: Requires authentication to access task-related resources.
- `/logs/**`: Requires authentication to access request-log-related resources.

### **Public Endpoints**:
The following endpoints do **not** require authentication and are publicly accessible:

- `/auth/**`: Open for signup and login functionality.
- `/info`: Open for api statistics.
- `/ping`: Open for health check purposes.
- `/error`: Open for error responses.
- `/swagger-ui/**`: Open for API documentation.
- `/v3/api-docs/**`: Open for API documentation.

---
## 🔢 API Request Counting
The API tracks the number of requests made to the `/task` endpoint for various HTTP methods such as `GET`, `POST`, `PUT`, `PATCH`, and `DELETE`. 

You can view the statistics for the counted requests at the `/logs` endpoint:

---
## 📊 App Info

There is an `/info` endpoint available that provides useful statistics about the current state of the application:

- **Task Count**: Displays the total number of tasks in the system.
- **Priority Breakdown**: Shows how many tasks exist for each priority level (High, Medium, Low).
- **Request Count**: Provides a count of how many requests have been made for each HTTP method (GET, POST, PUT, PATCH, DELETE) to the `/task` endpoint.

---
© 2025 Piotr Stefanski.