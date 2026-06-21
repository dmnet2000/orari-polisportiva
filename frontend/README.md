# Frontend - Orari Polisportiva

Applicazione frontend Vue.js 3 per la gestione degli orari degli impianti sportivi.

## Prerequisiti

- Node.js 18+
- npm oppure yarn

## Setup

### Installazione dipendenze

```bash
npm install
```

### Sviluppo

```bash
npm run dev
```

L'applicazione sarà disponibile su `http://localhost:3000`

### Build

```bash
npm run build
```

### Preview

```bash
npm run preview
```

## Variabili d'ambiente

Crea un file `.env` nella cartella frontend:

```env
VITE_API_URL=http://localhost:8080/api
```

## Funzionalità

- Autenticazione utente (login/registrazione)
- Visualizzazione palestre
- Navigazione discipline sportive
- Visualizzazione orari di allenamento disponibili
- Creazione e gestione prenotazioni
- Design responsive

## Struttura del progetto

- `src/main.js` - Punto di ingresso dell'applicazione
- `src/App.vue` - Componente root
- `src/router/` - Configurazione router
- `src/stores/` - State management con Pinia
- `src/services/` - Moduli servizio API
- `src/views/` - Componenti pagina
- `src/components/` - Componenti riutilizzabili
