# com.propydis

## 🚀 Web Application for Architecture Studio

```text
This project is a web application composed of two independent modules:
- Backend: A RESTful API built with Spring Boot, responsible for business logic, data persistence, security management, role-based access control, and endpoint exposure.
- Frontend: A user interface developed with React + Vite, designed to consume the API and deliver a fast, modern, and responsive experience.
The project is currently under active development and is intended to be deployed to production upon completion.

```
## 🎯 Purpose

```text
The application is designed for an architecture studio that needs to showcase to its clients:
- Properties available for sale or rent.
- Architectural projects completed by the studio.
The system supports multiple user roles, enabling differentiated content management and functionality based on access level (e.g., administrator, client, visitor).

```

## 📚 Documentation

```text
This repository includes detailed technical documentation located in the docs/folder:
• 	 – Overview of the system architecture, including module separation, data flow, and design decisions.
• 	 – Description of core business rules, use cases, and domain logic.
• 	 – Functional and non-functional requirements, including user stories and acceptance criteria.
• 	 – Sequence diagrams illustrating key interactions between components and user flows.
These documents are intended to provide clarity for future contributors, stakeholders, and deployment teams. They reflect the current development phase and will be updated as the project evolves.

```

## 🛠️ Tech Stack

```text
This project leverages a modern and robust technology stack to ensure scalability, security, and maintainability:
🔧 Backend – Spring Boot (Java 21)
- Spring Boot 3.5.4 – Core framework for building RESTful APIs
- Spring Data JPA & JDBC – Relational database access
- Spring Data MongoDB – NoSQL support
- JWT (JSON Web Tokens) – Stateless user authentication
- Spring Validation – Input validation
- SpringDoc OpenAPI – Auto-generated API documentation
- Logstash Logback Encoder – Structured logging
- Caffeine – In-memory caching
- Cloudinary – Media upload and management
- Dotenv – Environment variable management
- Lombok – Boilerplate code reduction
- MySQL Connector – Database driver
- DevTools – Hot reload during development
- JUnit, Mockito, Reactor Test – Testing framework

```

## ⚛️ Frontend – React + Vite

```text
• 	React – Component-based UI library
• 	Vite – Fast build tool and development server
• 	Axios / Fetch – API consumption
• 	React Router – Client-side routing
• 	Tailwind / Styled Components / CSS Modules – (depending on your styling choice)

```

## 🚀 Installation & Setup

### 🖥️ Local Development

```text
To run the project locally, follow these steps:
🔧 Backend (Spring Boot)

```
### - Clone the repository

```bash
git clone https://github.com/FlavioKde/com.propydis.studio.git
cd com.propydis.studio
```
### - Set environment variables

```text
You can use .env or configure application.properties to connect to your remote databases:

```bash
spring.datasource.url=jdbc:mysql://your-railway-host/dbname
spring.datasource.username=your-username
spring.datasource.password=your-password

spring.data.mongodb.uri=mongodb+srv://your-mongo-uri
```

### Run the application

```bash
./gradlew bootRun
```

## ⚛️ Frontend (React + Vite)

### - Clone the repository

```bash
git clone https://github.com/FlavioKde/com.propydis.studio.web.git
cd frontend-repo
```

### - Install dependencies

```bash
npm install
```

### - Configure API endpoint

```text
Set the backend URL in .env or directly in your API service:
```
```bash
VITE_API_URL=http://localhost:8080
```
### Run the frontend

```bash
npm run dev
```

### ☁️ Deployment (Render)

When deploying to Render, you’ll need to:
- Create a Web Service for the backend and set the environment variables (Mongo URI, MySQL credentials).
- Create a Static Site or Web Service for the frontend, depending on whether you’re building it or serving it dynamically.
- Ensure CORS is properly configured in your backend to allow requests from your frontend domain.
- Optionally, use Render’s environment groups to manage shared variables.
You can add a note like:
Note: The backend connects to external databases (MongoDB Atlas and Railway MySQL), so no local database setup is required.







## 📦 dependencies(build.gradle.kts)

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.5.4'
    id 'io.spring.dependency-management' version '1.1.0'
}
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web:3.5.4'
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb:3.5.4'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa:3.5.4'
    implementation 'org.springframework.boot:spring-boot-starter-validation:3.5.4'
    implementation 'org.springframework.security:spring-security-core:6.5.4'
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9'

    // JWT
    implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'

    // Lombok (opcional)
    compileOnly 'org.projectlombok:lombok:1.18.34'
    annotationProcessor 'org.projectlombok:lombok:1.18.34'
    testCompileOnly 'org.projectlombok:lombok:1.18.34'
    testAnnotationProcessor 'org.projectlombok:lombok:1.18.34'

    developmentOnly 'org.springframework.boot:spring-boot-devtools:3.5.4'

    runtimeOnly 'com.mysql:mysql-connector-j:8.1.0'

    // Testing
    testImplementation 'org.springframework.boot:spring-boot-starter-test:3.5.4'
    testImplementation 'org.mockito:mockito-core:5.11.0'
    testImplementation 'org.mockito:mockito-junit-jupiter:5.11.0'
    testImplementation 'io.projectreactor:reactor-test:1.5.14'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher:1.10.0'
}
```
