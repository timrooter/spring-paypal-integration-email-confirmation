# SpringBoot, Jwt, Paypal Integration, Email confirmation

[![Typing SVG](https://readme-typing-svg.herokuapp.com?color=%2339FF14&lines=SPRING-BOOT-JWT-EMAIL-PAYPAL)](https://git.io/typing-svg)


The goal of this project is to implement an application called `springboot-tasks` to manage your To-Do Tasks. Back-end `Spring Boot` using `Maven` build tool. The project uses [`JWT Authentication`](https://en.wikipedia.org/wiki/JSON_Web_Token) to secure application Users.

Basic functionality:

-`RESTful API`

-`PostgreSQL DB`

-`Login in Sign Up`

-`JWT Authentication Securing`

-`Managing Tasks`

-`Managing Users by Admin`

-`Swagger for checking API`

-`PayPal payments integration (Supposed to be as a Sponsorship)`

-`Email Confirmation with expiry`


### API server

  `Spring Boot` Web Java backend application that exposes a Rest API to create, retrieve and delete tasks. If a user has `ADMIN` role he/she can also retrieve information of other users or delete them.

  The application secured endpoints can just be accessed if a valid JWT access token is provided.

  The app stores its data in [`Postgres`](https://www.postgresql.org/) database.

### ENDPOINTS

| Endpoint                                                                 | Secured | Roles           |
|--------------------------------------------------------------------------|---------|-----------------|
| `POST /auth/authenticate -d {"username","password"}`                     | No      |                 |
| `POST /auth/signup -d {"username","password","name","email"}`            | No      |                 |
| `GET /auth/confirm`                                                      | Yes     | `ADMIN`, `USER` |
| `GET /public/numberOfUsers`                                              | No      |                 |
| `GET /public/numberOfOrders`                                             | No      |                 |
| `GET /api/users/me`                                                      | Yes     | `ADMIN`, `USER` |
| `GET /api/users`                                                         | Yes     | `ADMIN`         |
| `GET /api/users/{username}`                                              | Yes     | `ADMIN`         |
| `DELETE /api/users/{username}`                                           | Yes     | `ADMIN`         |
| `GET /api/tasks [?text]`                                                 | Yes     | `ADMIN`         |
| `POST /api/tasks -d {"title","description"}`                             | Yes     | `ADMIN`, `USER` |
| `DELETE /api/tasks/{id}`                                                 | Yes     | `ADMIN`, `USER` |
| `POST /payment/create -d {"method", "amount", "currency", "description"}` | No      |                 |
| `GET /payment/success`                                                   | No      |                 |
| `GET /payment/cancel`                                                    | No      |                 |
| `GET /payment/error`                                                     | No      |                 |

### Swagger
|        | URL                                   | 
|--------| ------------------------------------- | 
| Server | http://localhost:8080/swagger-ui.html |                                                                                            |

### Prerequisites

- [`Java 17+`](https://www.oracle.com/java/technologies/downloads/#java17)

### Additional dev tools

- `PayPal Developer Account` - Use SandBox for testing
- `Email SMTP server` - I use [mailtrap.io](https://mailtrap.io) as an email infrastructure
- `PostgreSQL` - Database

### Checking some API with POSTMAN

| Method | URI                                                                                                 | 
|--------|-----------------------------------------------------------------------------------------------------| 
| POST   | http://localhost:8080/payment/create?method=PayPal&amount=10.0&currency=USD&description=Sponsorship | 

<img width="1030" alt="Снимок экрана 2024-05-01 в 00 57 22" src="https://github.com/timrooter/spring-paypal-integration-email-confirmation/assets/146642629/072290d0-01d4-4bea-9bf4-9cf3d605170c">


|      | URI                                                                                                | 
|------|----------------------------------------------------------------------------------------------------| 
| POST | http://localhost:8080/auth/signup | 
```
JSON data-raw 
{
"username": "timrooter",
"password": "qwerty12345",
"name": "John Johnson",
"email": "timrooter@dev.com"
}
```
After Sending POST request, `accessToken` will be returned and `Email Confirmation` will be sent on specified email.

<img width="1034" alt="Снимок экрана 2024-05-01 в 01 17 28" src="https://github.com/timrooter/spring-paypal-integration-email-confirmation/assets/146642629/5bccc24b-fcda-4698-a3a2-fbc2a3f9f4cb">

It should return
```
    Code: 200
    { "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9..." }
```



