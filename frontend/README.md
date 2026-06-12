# Frontend - Orari Polisportiva

Vue.js 3 frontend application for managing sports facility schedules.

## Prerequisites

- Node.js 18+
- npm or yarn

## Setup

### Install Dependencies

```bash
npm install
```

### Development

```bash
npm run dev
```

The application will be available at `http://localhost:3000`

### Build

```bash
npm run build
```

### Preview

```bash
npm run preview
```

## Environment Variables

Create a `.env` file in the frontend directory:

```env
VITE_API_URL=http://localhost:8080/api
```

## Features

- User authentication (login/register)
- View sports facilities (palestre)
- Browse sports disciplines
- View available training schedules
- Make and manage bookings
- Responsive design

## Project Structure

- `src/main.js` - Application entry point
- `src/App.vue` - Root component
- `src/router/` - Router configuration
- `src/stores/` - Pinia state management
- `src/services/` - API service modules
- `src/views/` - Page components
- `src/components/` - Reusable components
