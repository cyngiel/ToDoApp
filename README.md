# ToDoApp



This is a **Todo** application designed to manage tasks.

## 🎉 Key Features:
- **Add Tasks**: Create new tasks by providing a title, description, and setting the task's priority (High, Medium, or Low).
- **View Tasks**: Browse through tasks and view their details.
- **Modify and Delete Tasks**: Edit the title, description, or priority level.
- **Request Counting**: Track the number of requests made to the API
- **Security:** Secured by JWT token and User Roles

## ⚙️ Technologies Used
- **Spring Boot** for the backend
- **Swagger UI** for API documentation
- **Spring Data** for database access
- **Spring Security** for security and role management
- **JSON Web Token** for authentication
- **Docker** for deployment on a Docker container
- **H2 Database** for in-memory storage (can be replaced with MySQL in production)
- **Lombok** for reducing boilerplate code

---

## 🚀 Getting Started

To run the application on your machine using Docker, run the following commands in the project root directory::

### 1. Build the Docker image

```bash
docker build --tag=todoapp:latest .
```

### 2. Run the Docker container
```bash
docker run -p 8080:8080 todoapp
```


## 📄 API Documentation

> **🚀 Swagger UI - API Documentation**  
> You can explore and test the API using **Swagger UI**. This interactive tool provides a clear overview of all available API endpoints, allowing you to test them directly from the browser.
> To access the Swagger UI, simply visit the following URL in your browser:
> [Swagger UI - API Documentation](http://localhost:8080/swagger-ui/index.html#/)


## 🌐 API Access

The API is available at the default port:  [http://localhost:8080](http://localhost:8080)
and secured using a JWT token, ensuring that only authenticated users with a valid token can access protected endpoints.

#### **Sign Up**
To create a new account, send a `POST` request to the `/signup` endpoint with the necessary user details.

#### **Login**
To Sign in and obtain a token for accessing protected resources, send a `POST` request to the `/login` endpoint with your login credentials.



## 🔑 Test Users For Testing Purposes

For convenience, a **test user** and **admin** is pre-configured so you can start testing without creating a new account.

**Test User Credentials:**
- **Username**: `test`
- **Password**: `test`

**Admin Credentials:**
- **Username**: `admin`
- **Password**: `admin`

Log in using these credentials at the `/login` endpoint to receive a JWT token for testing.

## 🔒 Authentication Required Endpoints

The following endpoints require authentication. To access these, you must provide a valid authentication token in the request header:

### **Protected Endpoints**:
- `/tasks/**`: Requires authentication to access task-related resources.
- `/logs/**`: Requires authentication to access request-log-related resources.
- `/admin/tasks`: Requires authentication and **admin** role to view and manage all tasks.

### **Public Endpoints**:
The following endpoints do **not** require authentication and are publicly accessible:

- `/auth/**`: Open for signup and login functionality.
- `/info`: Open for api statistics.
- `/ping`: Open for health check purposes.
- `/error`: Open for error responses.
- `/swagger-ui/**`: Open for API documentation.
- `/v3/api-docs/**`: Open for API documentation.

---
## 📝Tasks Management

The API provides several endpoints to manage tasks, enabling you to create, read, update, and delete tasks.

- **Get All Tasks**  
   `GET /tasks` allows to retrieve a list of all tasks. Filtered by **priority** (optional) and sort them by a specific field using the `sortBy` parameter (optional). Additionally, the `desc` parameter toggle between to ascending and descending order. If no filters or sorting options are provided, all tasks are returned in their default order.

  - **Query Parameters**:
    - `priority` (optional, enum): Filter tasks by their priority level (`HIGH`,`MEDIUM`,`LOW`)
    - `sortBy` (optional, enum): Sort tasks by a specific field (`TITLE`, `CREATED`, `PRIORITY`).
    - `desc` (optional, boolean): Sort tasks in descending order when `true` (default is ascending).

- **Get Task by ID**  
   `GET /tasks/{id}` endpoint retrieves a task by its unique identifier (`id`). 

- **Create Tasks**  
   `POST /tasks` endpoint allows to create one or more new tasks,sent as a list in the request body.

- **Update Task**  
   `PUT /tasks/{id}` endpoint updates an existing task based on its unique identifier (`id`). Must provide the new details in the request body.

- **Get Task Count by Priority**  
   `GET /tasks/count/{priorityString}` endpoint allows to retrieve the number of tasks that match a specific priority (`HIGH`, `MEDIUM`, `LOW`). 

- **Delete Task**  
   `DELETE /tasks/{id}` endpoint allows to delete a task by its ID (`id`).

These endpoints allow for full CRUD (Create, Read, Update, Delete) operations on tasks, offering flexibility to interact with the task management system.


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
## ⚠️ **Important**: The application uses a **test-mode database** 

- Database setup is designed to make testing easier and cleaner by resetting the environment on each run.
- The database is **reset** every time the application stops. This means that once the app shuts down, any tasks, users, or other data will be erased.
- Upon each launch of the application, the database is populated with **test data**. This includes predefined tasks and test users, for a quick start interacting with the app without needing to manually add data.

---
© 2025 Piotr Stefanski.