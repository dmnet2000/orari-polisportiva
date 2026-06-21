# Backend - Orari Polisportiva

Applicazione Spring Boot per la gestione degli orari degli impianti sportivi.

## Prerequisiti

- Java 17+
- Maven 3.8+
- PostgreSQL 14+

## Setup

### Configurazione Database

Aggiorna `src/main/resources/application.yml` con le credenziali del tuo database.

### Build & Avvio

```bash
# Build
mvn clean install

# Avvio
mvn spring-boot:run

# API disponibili su: http://localhost:8080/api
# Swagger UI: http://localhost:8080/api/swagger-ui.html
```

## Endpoint API

### Autenticazione
- `POST /api/auth/register` - Registra un nuovo utente
- `POST /api/auth/login` - Login utente

### Palestre
- `GET /api/palestre` - Ottiene tutte le palestre
- `GET /api/palestre/{id}` - Ottiene palestra per ID
- `POST /api/palestre` - Crea nuova palestra (Admin)
- `PUT /api/palestre/{id}` - Aggiorna palestra (Admin)
- `DELETE /api/palestre/{id}` - Elimina palestra (Admin)

### Discipline
- `GET /api/discipline` - Ottiene tutte le discipline sportive
- `GET /api/discipline/{id}` - Ottiene disciplina per ID
- `POST /api/discipline` - Crea nuova disciplina (Admin)
- `PUT /api/discipline/{id}` - Aggiorna disciplina (Admin)
- `DELETE /api/discipline/{id}` - Elimina disciplina (Admin)

### Gruppi
- `GET /api/gruppi` - Ottiene tutti i gruppi
- `GET /api/gruppi/{id}` - Ottiene gruppo per ID
- `POST /api/gruppi` - Crea nuovo gruppo (Admin)
- `PUT /api/gruppi/{id}` - Aggiorna gruppo (Admin)
- `DELETE /api/gruppi/{id}` - Elimina gruppo (Admin)

### Orari
- `GET /api/orari` - Ottiene tutti gli orari
- `GET /api/orari/{id}` - Ottiene orario per ID
- `POST /api/orari` - Crea nuovo orario (Admin)
- `PUT /api/orari/{id}` - Aggiorna orario (Admin)
- `DELETE /api/orari/{id}` - Elimina orario (Admin)

### Prenotazioni
- `GET /api/prenotazioni` - Ottiene tutte le prenotazioni
- `GET /api/prenotazioni/{id}` - Ottiene prenotazione per ID
- `POST /api/prenotazioni` - Crea nuova prenotazione (Utente)
- `PUT /api/prenotazioni/{id}` - Aggiorna prenotazione (Utente)
- `DELETE /api/prenotazioni/{id}` - Annulla prenotazione (Utente)

## Architettura

- **Entity Layer**: entità JPA con relazioni
- **Repository Layer**: repository Spring Data JPA
- **Service Layer**: logica di business
- **Controller Layer**: endpoint REST
- **Security**: autenticazione basata su JWT con Spring Security
