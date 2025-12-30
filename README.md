# JWT Authentication API

A Spring Boot REST API that implements JWT-based authentication and authorization.

## Technologies Used
- Java 21+
- Spring Boot
- Spring Security
- JWT (jjwt)
- Spring Data JPA
- PostgreSQL
- Maven
- Swagger

## Features
- User registration
- User login with JWT token
- Email verification
- Secured REST endpoints
- Stateless authentication
- Environment-based configuration (.env)

## Authentication Flow
1. User registers with email, username, and password.
2. A verification email is sent to the user.
3. After verification, the user logs in and receives a JWT token.
4. The client sends the token in the request header
5. Secured endpoints validate incoming requests by applying a JWT authentication filter.


## Details
Sensitive data (database credentials, JWT secret, email configuration) is stored in environment variables and excluded from version control.
