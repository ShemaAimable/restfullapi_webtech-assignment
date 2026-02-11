# restfullapi_webtech-assignment
Repository: restfull_API_ASSIGNMENT
Branch for submission: restFull_api_26312
Author: SHEMA AIMABLE / 26312

This repository contains Spring Boot RESTful APIs built for practical exercises in the module "Introduction to Spring Boot & Building REST Controllers". Each question is implemented as a separate Spring Boot project with sample data and REST endpoints.

Table of Contents

Question 1: Library Book Management API

Question 2: Student Registration API

Question 3: Restaurant Menu API

Question 4: E-Commerce Product API

Question 5: Task Management API

Bonus: User Profile API

Running the Applications

Testing

Question 1: Library Book Management API

Project: question1-library-api
Base URL: http://localhost:8080/api/books

Method	URL	Description
GET	/api/books	Get all books
GET	/api/books/1	Get book by ID
GET	/api/books/search?title=Clean	Search books by title
POST	/api/books	Add new book (JSON body)
DELETE	/api/books/1	Delete book by ID

Sample POST Body:

{
  "id": 4,
  "title": "Refactoring",
  "author": "Martin Fowler",
  "isbn": "978-0201485677",
  "publicationYear": 1999
}

Question 2: Student Registration API

Project: question2-student-api
Base URL: http://localhost:8080/api/students

Method	URL	Description
GET	/api/students	Get all students
GET	/api/students/1	Get student by ID
GET	/api/students/major/Computer Science	Get students by major
GET	/api/students/filter?gpa=3.5	Filter students by GPA ≥ 3.5
POST	/api/students	Register new student
PUT	/api/students/2	Update student info

Sample POST Body:

{
  "studentId": 6,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "major": "Mathematics",
  "gpa": 3.7
}

Question 3: Restaurant Menu API

Project: question3-restaurant-api
Base URL: http://localhost:8080/api/menu

Method	URL	Description
GET	/api/menu	Get all menu items
GET	/api/menu/3	Get menu item by ID
GET	/api/menu/category/Main Course	Filter by category
GET	/api/menu/available?available=true	Get only available items
GET	/api/menu/search?name=choco	Search menu items by name
POST	/api/menu	Add new menu item
PUT	/api/menu/4/availability	Toggle availability
DELETE	/api/menu/8	Delete menu item

Sample POST Body:

{
  "id": 9,
  "name": "Cheesecake",
  "description": "Creamy cheesecake",
  "price": 6.5,
  "category": "Dessert",
  "available": true
}

Question 4: E-Commerce Product API

Project: question4-product-api
Base URL: http://localhost:8080/api/products

Method	URL	Description
GET	/api/products	Get all products
GET	/api/products?page=1&limit=5	Get products with pagination
GET	/api/products/1	Get product by ID
GET	/api/products/category/Electronics	Filter by category
GET	/api/products/brand/Apple	Filter by brand
GET	/api/products/search?keyword=phone	Search by keyword
GET	/api/products/price-range?min=100&max=2000	Filter by price range
GET	/api/products/in-stock	Products in stock
POST	/api/products	Add new product
PUT	/api/products/1	Update product
PATCH	/api/products/1/stock?quantity=20	Update stock
DELETE	/api/products/1	Delete product

Sample POST Body:

{
  "productId": 11,
  "name": "Google Pixel 8",
  "description": "Latest Google phone",
  "price": 900,
  "category": "Electronics",
  "stockQuantity": 15,
  "brand": "Google"
}

Question 5: Task Management API

Project: question5-task-api
Base URL: http://localhost:8080/api/tasks

Method	URL	Description
GET	/api/tasks	Get all tasks
GET	/api/tasks/1	Get task by ID
GET	/api/tasks/status?completed=true	Filter tasks by completion
GET	/api/tasks/priority/HIGH	Filter tasks by priority
POST	/api/tasks	Create new task
PUT	/api/tasks/2	Update task
PATCH	/api/tasks/2/complete	Mark task as completed
DELETE	/api/tasks/2	Delete task

Sample POST Body:

{
  "taskId": 5,
  "title": "Study Spring Boot",
  "description": "Complete RESTful API exercises",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-20"
}

Bonus: User Profile API

Project: bonus-userprofile-api
Base URL: http://localhost:8080/api/users

Method	URL	Description
GET	/api/users	Get all user profiles
GET	/api/users/1	Get user by ID
POST	/api/users	Create user profile
PUT	/api/users/2	Update user profile
PATCH	/api/users/3/activate	Activate user
PATCH	/api/users/4/deactivate	Deactivate user
DELETE	/api/users/5	Delete user

Sample POST Body:

{
  "userId": 6,
  "username": "jane_doe",
  "email": "jane@example.com",
  "fullName": "Jane Doe",
  "age": 22,
  "country": "USA",
  "bio": "Software Engineering student",
  "active": true
}

Running the Applications

Open project in VS Code or IntelliJ IDEA.

Make sure you have Java 17+ installed.

Run the main class in each project (QuestionX...Application.java).

Application starts on http://localhost:8080/
 by default.

Use Postman to test endpoints.

Testing

Each project includes sample data to test GET, POST, PUT, PATCH, DELETE.

Include screenshots of Postman calls for submission.

All endpoints return proper HTTP status codes: 200 OK, 201 CREATED, 204 NO CONTENT, 404 NOT FOUND.
