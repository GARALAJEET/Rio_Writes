# ✒️ Rio Write - Blogging Platform

A modern, robust blogging platform built with Spring Boot, designed to provide a seamless and secure experience for creating, sharing, and discovering content.

---

## 📚 Overview

> A single-page application designed with a sticky navigation bar and smooth-scroll UI. Built using **Vanilla JavaScript** and **Tailwind CSS**, this project showcases the static backend documentation in a clear and interactive way.

---

## 🔑 Key Features

> Everything you need in a modern blogging platform:

| Feature | Description |
|--------|-------------|
| 👤 **User & Profile Management** | Secure user registration, JWT-based authentication, and profile management. |
| ✍️ **Post Management** | Full CRUD support for blog posts. |
| 💬 **Comment System** | Discussion-enabled comment management. |
| 🎨 **Customizable Templates** | Various UI templates to customize blogs. |
| 📝 **Rich Text Editor** | Easy formatting with a powerful editor. |
| 🔍 **Search Functionality** | Quick search across users, posts, and comments. |
| 🔒 **Security** | Spring Security with protected API endpoints. |
| 🚀 **Scalability** | Optimized for high traffic and large user bases. |

---

## 🧰 Technology Stack

| Layer        | Tools & Frameworks               |
|-------------|----------------------------------|
| **Backend** | Spring Boot, Spring Security     |
| **Database**| MySQL                            |
| **Language**| Java 11+                         |
| **Build Tool**| Maven                          |
| **Server**   | Apache Tomcat                   |

---

## ⚙️ Getting Started

> Follow these steps to set up and run the project locally.

### ✅ Prerequisites

- Java Development Kit (JDK) 11 or higher  
- Apache Maven  
- A Database (MySQL, PostgreSQL, or H2)  
- IDE (IntelliJ, Eclipse, or similar)

---

### 🛠 Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/GARALAJEET/Rio_Writes.git
   cd rio-write
   ```

2. **Configure the database**

   Update your `application.properties`:
   ```
   src/main/resources/application.properties
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   

---



## 📁 Project Structure

```bash
rio-write/
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com/rio/Blogging/website
    │   │       ├── backgroudScheduled
    │   │       ├── Controller
    │   │       ├── DTO
    │   │       ├── exception
    │   │       ├── feature
    │   │       ├── Modal
    │   │       ├── repo
    │   │       ├── ReqObj
    │   │       ├── resMsg
    │   │       ├── Response
    │   │       ├── security
    │   │       ├── service
    │   │       └── ServiceImp
    │   └── resources
    │       └── application.properties
    └── test
        └── java
```

---

 Jeet Garala
