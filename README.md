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

- Set the backend URL in .env or directly in your API service:

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

⚠️ **Warning:**: The backend connects to external databases (MongoDB Atlas and Railway MySQL), so no local database setup is required.

### 📦 API Reference

This project uses SpringDoc OpenAPI to automatically generate and expose interactive API documentation via Swagger UI.
Once the backend is running, you can access the documentation at:

```bash
http://localhost:8080/swagger-ui.html
```
Or, if deployed

```bash
http://your-back-domain.com/swagger-ui.html
```

### The API includes endpoints for:

- 🔐 Authentication & Authorization
- POST /auth/login

- 🏘️ Property
- GET /property/{id}
- POST /Admin/property/save (admin only)
- GET /property/getAll
- PUT /admin/property/{id} (admin only)
- DELETE /admin/property/delete/{id} (admin only)
  
- 🏗️ Projects

- GET /project/{id}
- POST /admin/project/save (admin only)
- GET /project/getAll

All endpoints follow RESTful conventions and return standard HTTP status codes. Authentication is handled via JWT tokens, and role-based access is enforced across protected routes.


## 👥 Roles & Permissions

The application currently supports two user roles:
- 🕵️‍♂️ Visitor (Reader)
- No authentication required
- Can browse public content such as available properties and architectural projects
- Cannot access the dashboard or perform any administrative actions
- 🛠️ Admin
- Requires authentication via login
- Full access to the dashboard
- Can create, edit, and delete properties and projects
- Manages media uploads and content visibility
- Future scope includes user management and role assignment

⚠️ **Warning:**: Role-based access control is enforced via Spring Security and JWT authentication.

## 📈 Roadmap & Status

The project is actively evolving. Below is a summary of current progress and planned features:

✅ v0.1 – Current Version
- Public-facing frontend with property and project listings
- Admin dashboard for content management
- Role-based access: Visitor (reader) and Admin
- Integration with MongoDB Atlas and Railway MySQL
- JWT-based authentication (admin only)
- API documentation via Swagger

🧪 v0.2 – In Progress
- WebSocket-based chat between registered users and admin
- New role: Registered User with access to chat features
- User homepage with personalized content
- Admin panel extended to manage users and roles

🔮 v0.3 – Planned
- Refactor architecture into microservices
- Separate services for authentication, property management, and messaging
- Improved scalability and deployment flexibility

⚠️ **Warning:**: All roadmap items are subject to refinement based on feedback and presentation outcomes.

## 🤝 Contributing
Contributions are welcome! If you'd like to improve the project, fix bugs, or suggest new features, feel free to fork the repository and submit a pull request.
Please make sure to follow the existing code style and include relevant documentation when applicable.

## 📄 License
This project is licensed under the MIT License.
You are free to use, modify, and distribute it with proper attribution.

## 📬 Contact

For questions, feedback, or collaboration inquiries:
- Author: Flavio
- Location: Barcelona, Cataluña, España
- Email: [flaviodavirro@gmail.com]
- LinkedIn / GitHub: [https://github.com/FlavioKde]








