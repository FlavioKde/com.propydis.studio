# 🏛️ Architecture App - Business Logic

## 🎯 Purpose
```text

Aplication web for displaying architectural projects and properties for sale, with differentiated roles and content management.

```	
## 🧱 Entities

```text

- **Project**: id, name, description, List<Photo>, CreatedAt, UpdatedAt, status
- **Property**: id, name, description, List<Photo>, CreatedAt, UpdatedAt, status, PriceValue, PriceText
- **User**: id, username, email, passwordHash
- **Role**: id,name <<enum: READER, USER, ADMIN>>
- **Contact**: id, name, email, message, CreatedAt, status
- **Photos**: id, url, altText, publicId
- **ProjectStatus**: ACTIVE, ARCHIVED
- **PropertyStatus**: AVAILABLE, RESERVED, SOLD
- **ContactStatus**: NEW, VIEWED, REPLIED

```
## 👥 Roles and permissions

```text

| Rol     | Allowed actions			   |
|---------|----------------------------|
| Reader  | Sail without details       |
| User    | View full details          |
| Admin   | Complete content CRUD      |

```
## 🔐 Security

```text

- Autentication with JWT
- Security filter in backend
- Roles defined in the database

```
## 🧠 Business Logic

```text

- Data validation before persisting
- Access control by role
- Photo management as URLs
- CRUD for projects and properties
- Sending messages from contact

```
---
Author: [FlavioKde](https://github.com/FlavioKde)




