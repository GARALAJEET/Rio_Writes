# 📄 Rio Writes – (Backend Only)

A Spring Boot-based backend for a billing and content management platform. This project enables user registration, category and post management, and comment handling — designed for future scalability with role-based access and API security.

---

## 🚀 Features

### 🔐 Authentication & User Management
- **Sign Up**: Register a new user (`/api/newUser`)
- **Email OTP Verification**: Sends OTP via email for verification (`/api/verifyOTP`)
- **Post-Verification Access**: Only verified users can access full site features

#### User Operations:
- Create new users
- View all users
- Update user profiles
- Delete user profiles

> **Planned:** Role-based access control (`admin`, `user`)

---

### 📦 Category Management
- **Add Category**: Create a new category
- **Get All Categories**: View all existing categories
- **Get Category By ID**
- **Update Category**
- **Delete Category**

#### Endpoints:
- `POST /api/category`
- `GET /api/category/all`
- `GET /api/category/{id}`
- `PUT /api/category/{id}`
- `DELETE /api/category/{id}`

---

### 📝 Post Management

Posts are tied to both users and categories.

- **Create Post**: Add a new post under a user and category
- **Get Post By ID**
- **Get All Posts**
- **Update Post**
- **Delete Post By ID**
- **Delete All Posts**

#### Get Posts:
- By User
- By Category
- By Title
- By Keyword

#### Endpoints:
- `POST /api/post`
- `GET /api/post/{id}`
- `GET /api/post/all`
- `PUT /api/post/{id}`
- `DELETE /api/post/{id}`
- `GET /api/post/user/{userId}`
- `GET /api/post/category/{categoryId}`
- `GET /api/post/title/{title}`
- `GET /api/post/search/{keyword}`

---

### 💬 Comment Management
- **Add Comment To Post**
- **Get Comments By Post ID**
- **Update Comment By ID**
- **Delete Comment By ID**
- **Delete All Comments**

#### Endpoints:
- `POST /api/comment`
- `GET /api/comment/post/{postId}`
- `PUT /api/comment/{commentId}`
- `DELETE /api/comment/{commentId}`
- `DELETE /api/comment/all`

---

## 🛠️ Tech Stack

| Component       | Technology       |
|----------------|------------------|
| Backend         | Spring Boot (Java) |
| Database        | MySQL             |
| Testing Tool    | Postman           |
| Email Service   | JavaMailSender (OTP) |

> **Planned Features**:
> - JWT-based API security
> - Admin role for advanced operations
> - Frontend integration

---



---

## 🧪 API Testing

Use **Postman** to test all endpoints. Ensure MySQL is running and configured in `application.properties`.

**Example Postman Flows:**
- **User Flow**: Sign up → Verify OTP → Access endpoints
- **Category & Post Flow**: Add category → Add post → Comment

---

## 📌 Setup Instructions

### Clone Repository
```bash
git clone https://github.com/GARALAJEET/Rio_Writes.git
cd rio-writes-backend
```

### Set up MySQL Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_user
spring.datasource.password=your_password
```

### Run the Application

```bash
mvn spring-boot:run
```

---

## ✉️ Author

**jeet Garala**  
Developer & Creator of Rio Writes

---
