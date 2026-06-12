# Backend - Orari Polisportiva

Spring Boot application for managing sports facility schedules.

## Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL 14+

## Setup

### Database Configuration

Update `src/main/resources/application.yml` with your database credentials.

### Build & Run

```bash
# Build
mvn clean install

# Run
mvn spring-boot:run

# API will be available at: http://localhost:8080/api
# Swagger UI: http://localhost:8080/api/swagger-ui.html
```

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user

### Palestre
- `GET /api/palestre` - Get all facilities
- `GET /api/palestre/{id}` - Get facility by ID
- `POST /api/palestre` - Create new facility (Admin)
- `PUT /api/palestre/{id}` - Update facility (Admin)
- `DELETE /api/palestre/{id}` - Delete facility (Admin)

### Discipline
- `GET /api/discipline` - Get all sports disciplines
- `GET /api/discipline/{id}` - Get discipline by ID
- `POST /api/discipline` - Create new discipline (Admin)
- `PUT /api/discipline/{id}` - Update discipline (Admin)
- `DELETE /api/discipline/{id}` - Delete discipline (Admin)

### Gruppi
- `GET /api/gruppi` - Get all groups
- `GET /api/gruppi/{id}` - Get group by ID
- `POST /api/gruppi` - Create new group (Admin)
- `PUT /api/gruppi/{id}` - Update group (Admin)
- `DELETE /api/gruppi/{id}` - Delete group (Admin)

### Orari
- `GET /api/orari` - Get all schedules
- `GET /api/orari/{id}` - Get schedule by ID
- `POST /api/orari` - Create new schedule (Admin)
- `PUT /api/orari/{id}` - Update schedule (Admin)
- `DELETE /api/orari/{id}` - Delete schedule (Admin)

### Prenotazioni
- `GET /api/prenotazioni` - Get all bookings
- `GET /api/prenotazioni/{id}` - Get booking by ID
- `POST /api/prenotazioni` - Create new booking (User)
- `PUT /api/prenotazioni/{id}` - Update booking (User)
- `DELETE /api/prenotazioni/{id}` - Cancel booking (User)

## Architecture

- **Entity Layer**: JPA entities with relationships
- **Repository Layer**: Spring Data JPA repositories
- **Service Layer**: Business logic
- **Controller Layer**: REST endpoints
- **Security**: JWT-based authentication with Spring Security
