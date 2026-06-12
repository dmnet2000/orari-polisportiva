# Development
Do not forget to:

1. **Setup Database**
   - Create PostgreSQL database: `orari_polisportiva`
   - Update credentials in `application.yml`

2. **Backend Development**
   ```bash
   cd backend
   mvn spring-boot:run
   ```
   - API: http://localhost:8080/api
   - Swagger: http://localhost:8080/api/swagger-ui.html

3. **Frontend Development**
   ```bash
   cd frontend
   npm install
   npm run dev
   ```
   - Frontend: http://localhost:3000

4. **Docker Deployment**
   ```bash
   docker-compose up -d
   ```

## Project Structure

```
orari-polisportiva/
├── backend/                          # Spring Boot Backend
│   ├── src/main/java/com/polisportiva/
│   │   ├── config/                  # Configuration classes
│   │   ├── controller/              # REST Controllers
│   │   ├── entity/                  # JPA Entities
│   │   ├── dto/                     # Data Transfer Objects
│   │   ├── repository/              # Spring Data Repositories
│   │   ├── service/                 # Business Logic
│   │   ├── security/                # JWT Security
│   │   ├── exception/               # Exception Handlers
│   │   └── PolisportivaApplication.java
│   ├── src/main/resources/
│   │   ├── application.yml          # Configuration
│   │   ├── db/migration/            # Flyway migrations
│   │   └── static/
│   ├── pom.xml                      # Maven dependencies
│   ├── Dockerfile
│   └── README.md
│
├── frontend/                         # Vue.js Frontend
│   ├── src/
│   │   ├── components/              # Reusable components
│   │   ├── views/                   # Page components
│   │   ├── router/                  # Vue Router config
│   │   ├── stores/                  # Pinia stores
│   │   ├── services/                # API services
│   │   ├── App.vue                  # Root component
│   │   ├── main.js                  # Entry point
│   │   ├── style.css                # Global styles
│   │   └── main.css
│   ├── public/
│   ├── index.html
│   ├── package.json
│   ├── vite.config.js
│   ├── Dockerfile
│   └── README.md
│
├── docker-compose.yml               # Docker orchestration
├── .gitignore
├── README.md                        # Main documentation
└── DEVELOPMENT.md                   # This file
```

## Key Features Implemented

✅ User Authentication (JWT)
✅ Facilities Management
✅ Sports Disciplines
✅ Training Groups
✅ Schedule Management
✅ Booking System
✅ Role-based Access Control (Admin/User/Guest)
✅ RESTful API with Swagger
✅ Responsive Frontend
✅ Docker Deployment
✅ Database Migrations

## Next Steps for Enhancement

- [ ] Email notifications for bookings
- [ ] Advanced filtering and search
- [ ] User profile management
- [ ] Payment integration
- [ ] Analytics dashboard
- [ ] Mobile app
- [ ] Automated testing (Unit & E2E)
- [ ] CI/CD pipeline

## Common Issues & Solutions

**Issue**: Port already in use
```bash
# Change ports in docker-compose.yml or backend/application.yml
```

**Issue**: Database connection failed
```bash
# Check PostgreSQL is running
# Verify credentials in application.yml
```

**Issue**: Frontend can't connect to backend
```bash
# Check VITE_API_URL in frontend/.env
# Ensure backend is running on port 8080
```
