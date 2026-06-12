# Orari Polisportiva

An integrated platform for managing sports facility schedules, training groups, and bookings.

## 🎯 Project Overview

Orari Polisportiva is a full-stack web application that allows sports facility managers to efficiently organize schedules and enables users to book training sessions. The system includes:

- **Facilities Management**: Register and manage sports facilities with multiple fields
- **Sports Disciplines**: Organize sports types and training groups
- **Schedule Management**: Create and manage training schedules
- **Booking System**: Allow users to reserve spots in training sessions
- **User Authentication**: Secure JWT-based authentication
- **Admin Dashboard**: Comprehensive management tools for administrators

## 🏗️ Architecture

### Backend
- **Framework**: Spring Boot 3.x
- **Database**: PostgreSQL 14+
- **Security**: Spring Security with JWT
- **API**: RESTful with OpenAPI/Swagger documentation
- **ORM**: JPA/Hibernate

### Frontend
- **Framework**: Vue.js 3
- **Build Tool**: Vite
- **State Management**: Pinia
- **HTTP Client**: Axios
- **Router**: Vue Router 4

### Deployment
- **Containerization**: Docker & Docker Compose
- **Database**: PostgreSQL in Docker

## 📦 Installation

### Prerequisites
- Docker & Docker Compose
- OR
- Java 17+, Node.js 18+, PostgreSQL 14+

### Quick Start with Docker

```bash
# Clone the repository
git clone https://github.com/dmnet2000/orari-polisportiva.git
cd orari-polisportiva

# Start all services
docker-compose up -d

# Access the application
# Frontend: http://localhost:3000
# Backend API: http://localhost:8080/api
# Swagger UI: http://localhost:8080/api/swagger-ui.html
# Database: localhost:5432
```

### Manual Setup

#### Database Setup

```bash
# Create PostgreSQL database
psql -U postgres

CREATE DATABASE orari_polisportiva;
CREATE USER polisportiva WITH PASSWORD 'polisportiva_password';
ALTER ROLE polisportiva SET client_encoding TO 'utf8';
ALTER ROLE polisportiva SET default_transaction_isolation TO 'read committed';
ALTER ROLE polisportiva SET default_transaction_deferrable TO on;
GRANT ALL PRIVILEGES ON DATABASE orari_polisportiva TO polisportiva;
```

#### Backend Setup

```bash
cd backend

# Build
mvn clean install

# Run
mvn spring-boot:run

# API will be available at http://localhost:8080/api
```

#### Frontend Setup

```bash
cd frontend

# Install dependencies
npm install

# Development
npm run dev

# Build for production
npm run build
```

## 🔐 Default Credentials

### Admin User (Create after first run)

You can create an admin user through the registration endpoint or modify the role directly in the database.

## 📚 API Documentation

### Authentication Endpoints

```
POST /auth/register    - Register new user
POST /auth/login       - Login and get JWT token
```

### Palestre (Facilities)

```
GET    /palestre              - Get all facilities
GET    /palestre/attive       - Get active facilities
GET    /palestre/{id}         - Get facility by ID
POST   /palestre              - Create facility (Admin)
PUT    /palestre/{id}         - Update facility (Admin)
DELETE /palestre/{id}         - Delete facility (Admin)
```

### Discipline (Sports)

```
GET    /discipline            - Get all disciplines
GET    /discipline/attive     - Get active disciplines
GET    /discipline/{id}       - Get discipline by ID
POST   /discipline            - Create discipline (Admin)
PUT    /discipline/{id}       - Update discipline (Admin)
DELETE /discipline/{id}       - Delete discipline (Admin)
```

### Gruppi (Training Groups)

```
GET    /gruppi                         - Get all groups
GET    /gruppi/attivi                  - Get active groups
GET    /gruppi/disciplina/{disciplinaId} - Get groups by discipline
GET    /gruppi/{id}                    - Get group by ID
POST   /gruppi                         - Create group (Admin)
PUT    /gruppi/{id}                    - Update group (Admin)
DELETE /gruppi/{id}                    - Delete group (Admin)
```

### Orari (Schedules)

```
GET    /orari                    - Get all schedules
GET    /orari/attivi             - Get active schedules
GET    /orari/palestra/{id}      - Get schedules by facility
GET    /orari/gruppo/{id}        - Get schedules by group
GET    /orari/giorno/{giorno}    - Get schedules by day
GET    /orari/{id}               - Get schedule by ID
POST   /orari                    - Create schedule (Admin)
PUT    /orari/{id}               - Update schedule (Admin)
DELETE /orari/{id}               - Delete schedule (Admin)
```

### Prenotazioni (Bookings)

```
GET    /prenotazioni                - Get all bookings
GET    /prenotazioni/orario/{id}    - Get bookings by schedule
GET    /prenotazioni/utente/{id}    - Get bookings by user
GET    /prenotazioni/{id}           - Get booking by ID
POST   /prenotazioni                - Create booking
PUT    /prenotazioni/{id}           - Update booking
DELETE /prenotazioni/cancel/{id}    - Cancel booking
DELETE /prenotazioni/{id}           - Delete booking (Admin)
```

## 🗄️ Database Schema

### Main Tables

- **users**: User accounts with roles
- **palestre**: Sports facilities
- **discipline**: Sports disciplines
- **gruppi**: Training groups
- **orari**: Training schedules
- **prenotazioni**: User bookings

## 🛠️ Technologies

### Backend
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Flyway (Database migrations)
- Lombok
- Swagger/OpenAPI
- JWT (JSON Web Tokens)
- PostgreSQL

### Frontend
- Vue.js 3
- Vite
- Pinia
- Vue Router
- Axios
- CSS3

### DevOps
- Docker
- Docker Compose
- PostgreSQL

## 📝 Environment Configuration

### Backend (application.yml)

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/orari_polisportiva
    username: polisportiva
    password: polisportiva_password

app:
  security:
    jwt:
      secret: your-super-secret-key-change-this
      expiration: 86400000
```

### Frontend (.env)

```env
VITE_API_URL=http://localhost:8080/api
```

## 🚀 Deployment

### Production Deployment

1. Update environment variables in `docker-compose.yml`
2. Change JWT secret to a secure value
3. Configure database credentials
4. Run: `docker-compose -f docker-compose.yml up -d`

### Database Backups

```bash
# Backup
docker exec orari-polisportiva-db pg_dump -U polisportiva orari_polisportiva > backup.sql

# Restore
docker exec -i orari-polisportiva-db psql -U polisportiva orari_polisportiva < backup.sql
```

## 📝 Development

### Running Tests

```bash
# Backend tests
cd backend
mvn test

# Frontend tests
cd frontend
npm run test:unit
```

### Code Quality

```bash
# Frontend linting
cd frontend
npm run lint
```

## 🐛 Troubleshooting

### Database Connection Issues

```bash
# Check PostgreSQL status
docker ps | grep postgres

# View logs
docker logs orari-polisportiva-db
```

### Backend Issues

```bash
# View logs
docker logs orari-polisportiva-backend

# Rebuild
docker-compose up --build -d backend
```

### Frontend Issues

```bash
# Clear node_modules and reinstall
cd frontend
rm -rf node_modules
npm install
npm run dev
```

## 📄 License

MIT License - See LICENSE file for details

## 👨‍💻 Author

Umile De Marco - [GitHub](https://github.com/dmnet2000)

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

For support, email: dmnet2000@gmail.com
