# Guida allo Sviluppo - Orari Polisportiva

## 🎯 Principi di Sviluppo

- Codice pulito e leggibile
- Naming conventions consistenti
- Documentazione inline dove necessaria
- Test unitari per logica critica
- Separazione delle responsabilità (layers)

## 📋 Prerequisiti

- Java 17+
- Node.js 18+
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL 14+ (se non usi Docker)
- IDE: IntelliJ IDEA o VS Code

## 🏗️ Architettura

### Backend (Spring Boot)

```
src/main/java/com/polisportiva/
├── config/              # Configurazioni (Security, Swagger, Database)
├── controller/          # REST endpoints
├── service/             # Business logic
├── repository/          # Data access (JPA)
├── entity/              # JPA entities
├── dto/                 # Data Transfer Objects
├── exception/           # Custom exceptions
├── security/            # Auth/Authorization
└── util/                # Utility classes
```

### Frontend (Vue.js)

```
src/
├── components/          # Componenti riutilizzabili
├── views/               # Pagine principali
├── router/              # Vue Router config
├── stores/              # Pinia state management
├── services/            # API calls
├── assets/              # Static files (CSS, images)
└── App.vue              # Root component
```

## 🔄 Git Workflow

1. **Branch naming**: `feature/nome-feature`, `bugfix/nome-bug`, `hotfix/nome-hotfix`
2. **Commit messages**: 
   - ✅ `feat: add new feature`
   - ✅ `fix: resolve issue`
   - ✅ `docs: update documentation`
   - ✅ `test: add unit tests`
   - ❌ `fixed stuff`, `updated code`

3. **Pull Requests**: 
   - Descrivi le modifiche
   - Aggiungi screenshots per UI changes
   - Richiedi review prima di merge

## 🚀 Workflow Locale

### Backend

```bash
# Build
cd backend
mvn clean install

# Run
mvn spring-boot:run

# Test
mvn test

# API Docs: http://localhost:8080/swagger-ui.html
```

### Frontend

```bash
# Setup
cd frontend
npm install

# Development
npm run dev

# Build
npm run build

# Test
npm run test
```

## 🗄️ Database Migrations

Le migration sono gestite con Flyway (o Liquibase).

```bash
# Le migration vengono eseguite automaticamente al startup di Spring Boot
# File di migration: backend/src/main/resources/db/migration/
```

## 📝 Convenzioni di Codice

### Java
- Package naming: `com.polisportiva.{module}.{submodule}`
- Class naming: PascalCase
- Method naming: camelCase
- Constants: UPPER_SNAKE_CASE
- Lines max: 120 characters

### Vue.js
- Component naming: PascalCase (file .vue)
- Props naming: camelCase
- Event naming: kebab-case
- Store naming: camelCase

## 🧪 Testing

### Backend
```bash
# Unit tests
mvn test

# Integration tests
mvn verify
```

### Frontend
```bash
# Unit tests
npm run test:unit

# E2E tests
npm run test:e2e
```

## 🔐 Autenticazione

- **Spring Security**: Configurazione di default
- **JWT Tokens**: Per API stateless
- **Roles**: ADMIN, USER, GUEST
- **Endpoint Protection**: `@PreAuthorize` annotations

## 📦 Dependencies Management

- Backend: `pom.xml`
- Frontend: `package.json` e `package-lock.json`

**Aggiornare dipendenze con cautela** - sempre testare dopo update.

## 🐛 Debugging

### Backend
- IntelliJ IDEA: Usa il debugger integrato
- Log: Configurato in `application.yml`

### Frontend
- DevTools browser
- Vue DevTools extension
- Console logging

## 📚 Risorse Utili

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Vue.js 3 Guide](https://vuejs.org/guide/introduction.html)
- [PostgreSQL Docs](https://www.postgresql.org/docs/)
- [Docker Docs](https://docs.docker.com/)

## ❓ FAQ

**P: Come cambio la porta del backend?**
R: Modifica `backend/src/main/resources/application.yml` - `server.port`

**P: Come si usa Swagger?**
R: Visita `http://localhost:8080/swagger-ui.html` quando il backend è running

**P: Come aggiungo una nuova entità nel database?**
R: 1) Crea Entity class, 2) Crea Repository, 3) Crea migration SQL, 4) Crea Service e Controller

## 📞 Support

Per domande o problemi, apri un Issue nel repository.
