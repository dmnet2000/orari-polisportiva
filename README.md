# Orari Polisportiva

Applicazione web full-stack per la gestione ottimale degli orari delle palestre e delle discipline sportive di una polisportiva.

## Stack Tecnologico

- **Frontend**: Vue.js 3 + Vite
- **Backend**: Java + Spring Boot
- **Database**: PostgreSQL
- **Build**: Maven
- **Containerizzazione**: Docker Compose
- **Documentazione API**: Swagger/OpenAPI

## Funzionalità Principali

- ✅ Gestione palestre e campi
- ✅ Gestione discipline sportive
- ✅ Gestione gruppi di allenamento
- ✅ Gestione orari e disponibilità
- ✅ Prenotazione slot orari
- ✅ Gestione utenti (clienti, admin)
- ✅ Dashboard amministrativa
- ✅ Visualizzazione calendario orari
- ✅ Autenticazione e autorizzazione

## Setup Iniziale

### Con Docker Compose (Consigliato)

```bash
# Clone il repository
git clone https://github.com/dmnet2000/orari-polisportiva.git
cd orari-polisportiva

# Avvia i servizi
docker-compose up -d

# Accedi all'applicazione
# Frontend: http://localhost:3000
# Backend API: http://localhost:8080
# Swagger API Docs: http://localhost:8080/swagger-ui.html
# PostgreSQL: localhost:5432
```

### Setup Manuale

#### Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

#### Frontend
```bash
cd frontend
npm install
npm run dev
```

#### Database
Crea un database PostgreSQL e esegui le migration.

## Documentazione

- [CODE.md](./CODE.md) - Linee guida per lo sviluppo
- [Backend README](./backend/README.md) - Setup e architettura backend
- [Frontend README](./frontend/README.md) - Setup e componenti frontend

## Struttura Progetto

```
orari-polisportiva/
├── backend/                 # Spring Boot application
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── frontend/                # Vue.js application
│   ├── src/
│   ├── package.json
│   └── Dockerfile
├── docker-compose.yml       # Orchestrazione servizi
├── README.md
└── CODE.md
```

## Contributi

Per contribuire al progetto, consulta [CODE.md](./CODE.md).

## Licenza

MIT
