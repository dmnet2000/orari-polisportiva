# Orari Polisportiva

Una piattaforma integrata per la gestione degli orari degli impianti sportivi, dei gruppi di allenamento e delle prenotazioni.

## 🎯 Panoramica del Progetto

Orari Polisportiva è un'applicazione web full-stack che permette ai gestori degli impianti sportivi di organizzare in modo efficiente gli orari e agli utenti di prenotare le sessioni di allenamento. Il sistema include:

- **Gestione Impianti**: registrazione e gestione di impianti sportivi con più campi
- **Discipline Sportive**: organizzazione dei tipi di sport e dei gruppi di allenamento
- **Gestione Orari**: creazione e gestione degli orari di allenamento
- **Sistema di Prenotazione**: possibilità per gli utenti di riservare posti nelle sessioni di allenamento
- **Autenticazione Utenti**: autenticazione sicura basata su JWT
- **Dashboard Admin**: strumenti completi di gestione per gli amministratori

## 🏗️ Architettura

### Backend
- **Framework**: Spring Boot 3.x
- **Database**: PostgreSQL 14+
- **Sicurezza**: Spring Security con JWT
- **API**: RESTful con documentazione OpenAPI/Swagger
- **ORM**: JPA/Hibernate

### Frontend
- **Framework**: Vue.js 3
- **Build Tool**: Vite
- **State Management**: Pinia
- **HTTP Client**: Axios
- **Router**: Vue Router 4

### Deployment
- **Containerizzazione**: Docker & Docker Compose
- **Database**: PostgreSQL in Docker

## 📦 Installazione

### Prerequisiti
- Docker & Docker Compose  
- OPPURE  
- Java 17+, Node.js 18+, PostgreSQL 14+

### Avvio rapido con Docker

```bash
# Clona il repository
git clone https://github.com/dmnet2000/orari-polisportiva.git
cd orari-polisportiva

# Avvia tutti i servizi
docker-compose up -d

# Accesso all'applicazione
# Frontend: http://localhost:3000
# Backend API: http://localhost:8080/api
# Swagger UI: http://localhost:8080/api/swagger-ui.html
# Database: localhost:5432
```

### Setup manuale

#### Configurazione Database

```bash
# Crea database PostgreSQL
psql -U postgres

CREATE DATABASE orari_polisportiva;
CREATE USER polisportiva WITH PASSWORD 'polisportiva_password';
ALTER ROLE polisportiva SET client_encoding TO 'utf8';
ALTER ROLE polisportiva SET default_transaction_isolation TO 'read committed';
ALTER ROLE polisportiva SET default_transaction_deferrable TO on;
GRANT ALL PRIVILEGES ON DATABASE orari_polisportiva TO polisportiva;
```

#### Setup Backend

```bash
cd backend

# Build
mvn clean install

# Avvio
mvn spring-boot:run

# Le API saranno disponibili su http://localhost:8080/api
```

#### Setup Frontend

```bash
cd frontend

# Installa dipendenze
npm install

# Sviluppo
npm run dev

# Build per produzione
npm run build
```

## 🔐 Credenziali predefinite

### Utente Admin (da creare al primo avvio)

Puoi creare un utente admin tramite l'endpoint di registrazione oppure modificare direttamente il ruolo nel database.

## 📚 Documentazione API

### Endpoint di Autenticazione

```
POST /auth/register    - Registra un nuovo utente
POST /auth/login       - Login e ottenimento token JWT
```

### Palestre (Impianti)

```
GET    /palestre              - Ottiene tutti gli impianti
GET    /palestre/attive       - Ottiene gli impianti attivi
GET    /palestre/{id}         - Ottiene impianto per ID
POST   /palestre              - Crea impianto (Admin)
PUT    /palestre/{id}         - Aggiorna impianto (Admin)
DELETE /palestre/{id}         - Elimina impianto (Admin)
```

### Discipline (Sport)

```
GET    /discipline            - Ottiene tutte le discipline
GET    /discipline/attive     - Ottiene le discipline attive
GET    /discipline/{id}       - Ottiene disciplina per ID
POST   /discipline            - Crea disciplina (Admin)
PUT    /discipline/{id}       - Aggiorna disciplina (Admin)
DELETE /discipline/{id}       - Elimina disciplina (Admin)
```

### Gruppi (Gruppi di allenamento)

```
GET    /gruppi                           - Ottiene tutti i gruppi
GET    /gruppi/attivi                    - Ottiene i gruppi attivi
GET    /gruppi/disciplina/{disciplinaId} - Ottiene gruppi per disciplina
GET    /gruppi/{id}                      - Ottiene gruppo per ID
POST   /gruppi                           - Crea gruppo (Admin)
PUT    /gruppi/{id}                      - Aggiorna gruppo (Admin)
DELETE /gruppi/{id}                      - Elimina gruppo (Admin)
```

### Orari (Orari di allenamento)

```
GET    /orari                    - Ottiene tutti gli orari
GET    /orari/attivi             - Ottiene gli orari attivi
GET    /orari/palestra/{id}      - Ottiene orari per impianto
GET    /orari/gruppo/{id}        - Ottiene orari per gruppo
GET    /orari/giorno/{giorno}    - Ottiene orari per giorno
GET    /orari/{id}               - Ottiene orario per ID
POST   /orari                    - Crea orario (Admin)
PUT    /orari/{id}               - Aggiorna orario (Admin)
DELETE /orari/{id}               - Elimina orario (Admin)
```

### Prenotazioni (Bookings)

```
GET    /prenotazioni                - Ottiene tutte le prenotazioni
GET    /prenotazioni/orario/{id}    - Ottiene prenotazioni per orario
GET    /prenotazioni/utente/{id}    - Ottiene prenotazioni per utente
GET    /prenotazioni/{id}           - Ottiene prenotazione per ID
POST   /prenotazioni                - Crea prenotazione
PUT    /prenotazioni/{id}           - Aggiorna prenotazione
DELETE /prenotazioni/cancel/{id}    - Annulla prenotazione
DELETE /prenotazioni/{id}           - Elimina prenotazione (Admin)
```

## 🗄️ Schema Database

### Tabelle principali

- **users**: account utente con ruoli
- **palestre**: impianti sportivi
- **discipline**: discipline sportive
- **gruppi**: gruppi di allenamento
- **orari**: orari di allenamento
- **prenotazioni**: prenotazioni utenti

## 🛠️ Tecnologie

### Backend
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Flyway (migrazioni database)
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

## 📝 Configurazione ambiente

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

## 🚀 Deploy

### Deploy in produzione

1. Aggiorna le variabili d'ambiente in `docker-compose.yml`
2. Cambia il secret JWT con un valore sicuro
3. Configura le credenziali del database
4. Esegui: `docker-compose -f docker-compose.yml up -d`

### Backup Database

```bash
# Backup
docker exec orari-polisportiva-db pg_dump -U polisportiva orari_polisportiva > backup.sql

# Ripristino
docker exec -i orari-polisportiva-db psql -U polisportiva orari_polisportiva < backup.sql
```

## 📝 Sviluppo

### Esecuzione test

```bash
# Test backend
cd backend
mvn test

# Test frontend
cd frontend
npm run test:unit
```

### Qualità del codice

```bash
# Linting frontend
cd frontend
npm run lint
```

## 🐛 Risoluzione problemi

### Problemi connessione Database

```bash
# Verifica stato PostgreSQL
docker ps | grep postgres

# Visualizza log
docker logs orari-polisportiva-db
```

### Problemi Backend

```bash
# Visualizza log
docker logs orari-polisportiva-backend

# Rebuild
docker-compose up --build -d backend
```

### Problemi Frontend

```bash
# Pulisci node_modules e reinstalla
cd frontend
rm -rf node_modules
npm install
npm run dev
```

## 📄 Licenza

Licenza MIT - vedi file LICENSE per i dettagli

## 👨‍💻 Autore

Umile De Marco - [GitHub](https://github.com/dmnet2000)

## 🤝 Contribuire

1. Fai un fork del repository  
2. Crea il tuo branch feature (`git checkout -b feature/AmazingFeature`)  
3. Esegui commit delle modifiche (`git commit -m 'Add some AmazingFeature'`)  
4. Esegui push sul branch (`git push origin feature/AmazingFeature`)  
5. Apri una Pull Request

## 📞 Supporto

Per supporto, email: dmnet2000@gmail.com
