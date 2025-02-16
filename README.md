# JournalApp

A simple and secure **Journal Application** that allows users to write, edit, and manage their daily journal entries. Built with **Spring Boot** and **React.js**, this app provides a seamless user experience with authentication and data persistence.

## Features
- **User Authentication** (Login/Signup)
- **Create, Edit, Delete Journal Entries**
- **Secure Storage of Data**
- **Responsive UI for All Devices**
- **Search & Filter Journal Entries**

## Tech Stack
### Backend:
- **Spring Boot** (REST APIs)
- **Spring Security** (Authentication & Authorization)
- **JPA & Hibernate** (Database Integration)
- **MySQL/PostgreSQL** (Database)


### Other Technologies:
- **JWT (JSON Web Token)** for secure authentication
- **Docker** for containerization (if applicable)

## Installation & Setup
### Prerequisites
Ensure you have the following installed:
- Java (JDK 17 or later)
- Node.js & npm
- MySQL/PostgreSQL (or any preferred database)

### Backend Setup
```sh
# Clone the repository
git clone https://github.com/ashutoshtiwari0928/JournalApp.git
cd JournalApp/backend

# Configure application.properties for database
# Run the Spring Boot application
mvn spring-boot:run
```

### Frontend Setup
```sh
cd ../frontend
npm install
npm start
```

## API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | /auth/signup | User Registration |
| POST   | /auth/login | User Login |
| GET    | /journals | Fetch all journal entries |
| POST   | /journals | Create a new journal entry |
| PUT    | /journals/{id} | Update a journal entry |
| DELETE | /journals/{id} | Delete a journal entry |

## Contribution
Contributions are welcome! Feel free to open an issue or submit a pull request.

## License
This project is licensed under the [MIT License](LICENSE).

## Contact
For queries, reach out at [ashutoshtiwari0928](https://github.com/ashutoshtiwari0928).
