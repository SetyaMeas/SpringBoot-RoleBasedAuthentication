<h1 align="center">Role-based Authentication - Spring Boot</h1>

A Role-based Authentication/Authorization service use to secure API access.

We have 2 roles with the following permissions:

- `ROLE_ADMIN`
  - CREATE USER
  - EDIT USER
  - DELETE USER
- `ROLE_USER`
  - VIEW USER

To grant a user full access to all available permissions, assign both `ROLE_ADMIN` and `ROLE_USER` roles to the user.

Tech Stack
---
- Java
- Spring Boot
- Maven
- Flyway
- JWT
- PostgreSQL

Project initialize
---

Configure your database here: [application.properties](./src/main/resources/application.properties) and [flyway.conf](./src/main/resources/flyway.conf)

1. Install dependencies

```bash
mvn clean install -DskipTests
```

2. Run Database migrations

```bash
mvn flyway:migrate
```

3. Run the application

```bash
mvn spring-boot:run
```

API Endpoints
---

<details close>

<summary>
Login
</summary>

**Endpoint**

```
POST /api/auth/login
```

**Request Headers**

| Header       | Value            |
| ------------ | ---------------- |
| Content-Type | application/json |

**Request Body**

```json
{
    "email": "admin@admin.com",
    "password": "adminadmin"
}
```

| Field    | Type   | Required |
| -------- | ------ | -------- |
| email    | string | Yes      |
| password | string | Yes      |

**Response - 200 OK**

```json
{
    "data": {
        "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG...",
        "tokenType": "Bearer"
    },
    "errorMessage": null
}
```
**Response - 401 UNAUTHORIZED**

```json
{
  "data": null,
  "errorMessage": "Invalid email or password"
}
```

</details>

---

<details close>

<summary>View All Users</summary>

**Endpoint**

```
GET /api/user/view-all
```

**Request Header**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `token` |

**Response - 200 OK**

```json
{
  "data": [
    {
      "userId": 1,
      "name": "ADMIN",
      "email": "admin@admin.com",
      "roles": [
        "ROLE_ADMIN",
        "ROLE_USER"
      ],
      "createdAt": "2026-05-15T18:01:45.100379Z"
    },
    {
      "userId": 2,
      "name": "user 1",
      "email": "user@gmail.com",
      "roles": [
        "ROLE_USER",
        "ROLE_ADMIN"
      ],
      "createdAt": "2026-05-15T18:02:28.119745Z"
    }
  ],
  "errorMessage": null
}
```

**Response - 401 UNAUTHORIZED**

```
No Response body
```

</details>

---

<details close>

<summary>Create User</summary>

**Endpoint**

```
POST /api/user
```

**Request Header**

| Header        | Value            |
| ------------- | ---------------- |
| Content-Type  | application/json |
| Authorization | Bearer `token`   |

**Request Body**

```json
{
    "name": "user 1",
    "email": "user@gmail.com",
    "password": "strongpwd"
}
```

| Field    | Type   | Required |
| -------- | ------ | -------- |
| name     | string | Yes      |
| email    | string | Yes      |
| password | string | Yes      |

**Response - 200 OK**

```json
{
  "data": true,
  "errorMessage": null
}
```

**Response - 401 UNAUTHORIZED**

```
No Response body
```

**Response - 409 CONFLICT**

```json
{
  "data": null,
  "errorMessage": "Email already existed"
}
```

**Response - 403 FORBIDDEN**

```json
{
  "data": null,
  "errorMessage": "You don't have permission to execute this action"
}
```

</details>

---

<details close>

<summary>Grand Admin Role</summary>

**Endpoint**

```
PUT /user/grand-admin?userId=2
```

**Request Header**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `token` |

**Query Parameter**

| Parameter | Type    | Require |
| --------- | ------- | ------- |
| userId    | Integer | Yes     |

**Response - 200 OK**

```json
{
  "data": true,
  "errorMessage": null
}
```

**Response - 401 UNAUTHORIZED**

```
No Response body
```

**Response - 403 FORBIDDEN**

```json
{
  "data": null,
  "errorMessage": "You don't have permission to execute this action"
}
```

</details>

---

HTTP Client
---

To test API, send the HTTP-request inside [client.http](./client.http) file.

Referrence: [javaguides](https://www.javaguides.net/2024/01/spring-boot-security-jwt-tutorial.html)