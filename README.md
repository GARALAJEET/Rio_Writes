💼 Rio Writes 

A powerful backend system built with Spring Boot for managing users, categories, posts, and comments. Designed for modern billing/content platforms with user verification, REST APIs, and future-ready architecture.

🧠 Project Overview
Rio Writes is a backend service that enables:

✅ User signup with OTP verification
✅ Role-ready user management (Admin/User)
✅ Category & post creation with filtering
✅ Commenting system on posts
✅ Clean REST API architecture
✅ Future-proof JWT Security (planned)

🧰 Tech Stack
Layer	Technology
Backend	Spring Boot
Database	MySQL
Email Service	JavaMailSender
API Testing	Postman
Architecture	RESTful APIs

🔐 User Authentication & Authorization
✅ Sign Up
Endpoint: POST /api/newUser

Registers a new user

📩 OTP Verification
Endpoint: POST /api/verifyOTP

Sends OTP to user's email and verifies them

👤 User Management
Create, Update, View, and Delete users

Planned: Role-based system (Admin, User)

📦 Category Management
APIs for users to manage content categories.

Feature	Endpoint
➕ Add	POST /api/category
📖 Get All	GET /api/category/all
🔍 Get by ID	GET /api/category/{id}
✏️ Update	PUT /api/category/{id}
🗑️ Delete	DELETE /api/category/{id}

📝 Post Management
Posts are linked to both users and categories.

Feature	Endpoint
➕ Create Post	POST /api/post
📖 Get All Posts	GET /api/post/all
🔍 Get Post by ID	GET /api/post/{id}
🧑 Get Posts by User	GET /api/post/user/{userId}
🏷️ Get Posts by Category	GET /api/post/category/{categoryId}
🔠 Get Posts by Title	GET /api/post/title/{title}
🔎 Search Posts by Keyword	GET /api/post/search/{keyword}
✏️ Update Post	PUT /api/post/{id}
🗑️ Delete Post by ID	DELETE /api/post/{id}
🗑️ Delete All Posts	DELETE /api/post/all

💬 Comment Management
Users can engage with posts through comments.

Feature	Endpoint
➕ Add Comment	POST /api/comment
📖 Get Comments by Post ID	GET /api/comment/post/{postId}
✏️ Update Comment by ID	PUT /api/comment/{commentId}
🗑️ Delete Comment by ID	DELETE /api/comment/{commentId}
🗑️ Delete All Comments	DELETE /api/comment/all

🔐 Security (Coming Soon)
JWT Authentication for secure API access

Role-based authorization (ADMIN, USER)

Spring Security integration


⚙️ Getting Started
🧬 Prerequisites
Java 17+
MySQL running locally
Maven

🛠️ Installation Steps
bash
Copy
Edit
# Clone the repo
git clone https://github.com/GARALAJEET/Rio_Writes.git

# Set up MySQL DB
# Update src/main/resources/application.properties with:
# spring.datasource.url, username, and password

# Run the app
mvn spring-boot:run
🧪 API Testing Guide
Use Postman to test the complete API flow:

Sign up → /api/newUser

Verify OTP → /api/verifyOTP

Use user ID to:

Create category

Add post

Add comments

Explore all fetch, update, delete routes

🧱 Planned Enhancements
✅ JWT Security for APIs

✅ Role-based access (Admin/User)

✅ Swagger for API documentation

👨‍💻 Author
👋 Jeet Garala
🔗 GitHub: [https://github.com/GARALAJEET]
📧 Email: [jeetgarala2603@gmail.com]
