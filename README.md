Rio Write ✒️
A modern, robust blogging platform for writers and readers.

📖 Overview
Rio Write is a full-featured blogging platform built with Spring Boot, designed to provide a seamless and secure experience for creating, sharing, and discovering content. It offers a rich set of features for both writers and administrators, from a powerful text editor to comprehensive user management.

✨ Key Features
👤 User & Profile Management: Secure user registration, authentication (JWT-based), and detailed user profile management.

✍️ Post Management: A complete CRUD (Create, Read, Update, Delete) system for blog posts.

💬 Comment System: A robust comment management system for engaging discussions on posts.

🎨 Customizable Templates: Choose from a variety of templates to give your blog a unique look and feel.

📝 Rich Text Editing: An intuitive, powerful editor for effortlessly formatting your content.

🔍 Powerful Search: Quickly find users, posts, or comments across the entire platform.

🔒 Robust Security: Built with Spring Security to protect user data and secure API endpoints.

Scalable Architecture: Designed to handle high traffic and a large number of users efficiently.

🧰 Tech Stack & Tools
Category

Technology / Tool

Backend

Spring Boot, Spring Security

Database

MySQL (or your configured database)

Language

Java (Version 11+)

Build Tool

Apache Maven

Server

Apache Tomcat (Embedded)

⚙️ Getting Started
Follow these instructions to get the project up and running on your local machine.

Prerequisites
Java Development Kit (JDK): Version 11 or higher.

Apache Maven: To manage project dependencies and build.

A Database Server: Such as MySQL, PostgreSQL, or H2.

An IDE of your choice (e.g., IntelliJ IDEA, Eclipse).

Installation & Setup
Clone the repository:

git clone https://github.com/GARALAJEET/Rio_Writes.git
cd rio-write

Configure the database:

Open the src/main/resources/application.properties file.

Update the spring.datasource.url, spring.datasource.username, and spring.datasource.password properties to match your local database setup.

Build the project:

Use Maven to compile the code and download dependencies.

mvn clean install

Run the application:

You can run the project using the Spring Boot Maven plugin.

mvn spring-boot:run

The application will start on http://localhost:8080.



# Run all unit and integration tests
mvn test

📁 Project Structure
rio-write/
├── pom.xml                 # Maven Configuration
└── src
    ├── main
    │   ├── java
    │   │   └── com/rio/Blogging/website
    │   │       ├── backgroudScheduled # Scheduled background tasks
    │   │       ├── Controller      # REST API controllers
    │   │       ├── DTO             # Data Transfer Objects
    │   │       ├── exception       # Custom exception handlers
    │   │       ├── feature         # Feature-specific logic
    │   │       ├── Modal           # JPA Entity Models
    │   │       ├── repo            # Spring Data JPA repositories
    │   │       ├── ReqObj          # Request-specific objects
    │   │       ├── resMsg          # Response message constants
    │   │       ├── Response        # Response-specific objects
    │   │       ├── security        # JWT, Security Config, etc.
    │   │       ├── service         # Business logic interfaces
    │   │       └── ServiceImp      # Service implementations
    │   └── resources
    │       └── application.properties # Project configuration
    └── test
        └── java                    # Test source code

👤 Author
[Your Name]

📝 License
This project is licensed under the MIT License. See the LICENSE file for details.
