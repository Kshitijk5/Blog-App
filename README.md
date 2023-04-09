# Blog-App

This is a web application that allows users to create and manage their own blogs.
It provides a user-friendly interface for creating and editing posts, managing comments, and controlling user access based on two roles: ROLE_ADMIN and ROLE_USER.

## Features
* User registration and authentication using JWT authentication
* Two user roles: ROLE_ADMIN and ROLE_USER
* Users can create and update their own posts
* Users can comment on posts
* Users can delete their own posts and comments
* Admins can delete any posts and comments
* Input validation using Bean Validator
* Mapping between DTO and Entity objects using ModelMapper API
* Database storage using MySQL
* Build automation using Maven
* Lombok for reducing boilerplate code

## Requirements
To run the Spring Boot Blog App, you will need:

* Java 17 or higher
* MySQL

Installation
* Clone the repository using the following command:
```git clone https://github.com/Kshitijk5/Blog-App.git```
* Create a MySQL database named ```blogdb```.
* Set the database username and password in the application.properties file.
* Build the project using Maven by running the following command in the project directory:

```mvn clean package```
Run the application using the following command:

```java -jar target/spring-boot-blog-app-1.0.0.jar```

## Usage
* To access the application, go to http://localhost:8080/auth/login in your web browser.
* Use the registration page to create a new user account with either ROLE_ADMIN or ROLE_USER.
* Once logged in, users can create and manage their own posts, as well as comment on other users' posts.
* Admins can manage all posts and comments.

# Contributing
If you would like to contribute to my App, please fork the repository and submit a pull request.

# License
This project is licensed under the MIT License - see the LICENSE.md file for details.
