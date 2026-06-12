<template>
  <div class="home-container">
    <div class="hero">
      <h1>Benvenuto in Orari Polisportiva</h1>
      <p>Gestisci facilmente gli orari e le prenotazioni dei tuoi impianti sportivi</p>
      <div class="hero-buttons">
        <router-link v-if="!authStore.isAuthenticated" to="/register" class="btn btn-primary">Inizia Ora</router-link>
        <router-link to="/palestre" class="btn btn-secondary">Vedi Palestre</router-link>
      </div>
    </div>
    
    <div class="features">
      <div class="feature-card">
        <div class="feature-icon">🏢</div>
        <h3>Palestre</h3>
        <p>Gestisci tutte le tue strutture sportive in un unico posto</p>
      </div>
      <div class="feature-card">
        <div class="feature-icon">⚽</div>
        <h3>Discipline</h3>
        <p>Organizza le tue discipline sportive e i gruppi di allenamento</p>
      </div>
      <div class="feature-card">
        <div class="feature-icon">📅</div>
        <h3>Orari</h3>
        <p>Crea e gestisci facilmente gli orari dei tuoi corsi</p>
      </div>
      <div class="feature-card">
        <div class="feature-icon">📝</div>
        <h3>Prenotazioni</h3>
        <p>Consenti agli utenti di prenotare i loro posti</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useAuthStore } from '@/stores/authStore'

const authStore = useAuthStore()
</script>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  gap: 3rem;
}

.hero {
  text-align: center;
  padding: 3rem 0;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border-radius: 8px;
}

.hero h1 {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.hero p {
  font-size: 1.2rem;
  margin-bottom: 2rem;
}

.hero-buttons {
  display: flex;
  gap: 1rem;
  justify-content: center;
  flex-wrap: wrap;
}

.btn {
  padding: 0.75rem 2rem;
  border-radius: 4px;
  text-decoration: none;
  font-weight: bold;
  transition: all 0.3s;
  display: inline-block;
}

.btn-primary {
  background-color: #f39c12;
  color: white;
}

.btn-primary:hover {
  background-color: #e67e22;
  transform: translateY(-2px);
}

.btn-secondary {
  background-color: white;
  color: #667eea;
}

.btn-secondary:hover {
  background-color: #ecf0f1;
  transform: translateY(-2px);
}

.features {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 2rem;
}

.feature-card {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s;
}

.feature-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.feature-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.feature-card h3 {
  color: #2c3e50;
  margin-bottom: 0.5rem;
}

.feature-card p {
  color: #7f8c8d;
}
</style>
