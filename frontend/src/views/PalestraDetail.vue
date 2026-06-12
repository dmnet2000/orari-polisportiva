<template>
  <div class="palestra-detail-container" v-if="palestra">
    <div class="header">
      <h1>{{ palestra.nome }}</h1>
      <router-link to="/palestre" class="btn-back">← Torna alle palestre</router-link>
    </div>
    
    <div class="palestra-info">
      <div class="info-section">
        <h3>Informazioni Generali</h3>
        <p><strong>Indirizzo:</strong> {{ palestra.indirizzo }}</p>
        <p v-if="palestra.citta"><strong>Città:</strong> {{ palestra.citta }}</p>
        <p v-if="palestra.cap"><strong>CAP:</strong> {{ palestra.cap }}</p>
        <p v-if="palestra.telefono"><strong>Telefono:</strong> {{ palestra.telefono }}</p>
        <p v-if="palestra.email"><strong>Email:</strong> {{ palestra.email }}</p>
        <p><strong>Numero Campi:</strong> {{ palestra.numCampi }}</p>
        <p v-if="palestra.descrizione"><strong>Descrizione:</strong> {{ palestra.descrizione }}</p>
      </div>
    </div>

    <div class="orari-section">
      <h3>Orari Disponibili</h3>
      <div class="loading" v-if="loadingOrari">Caricamento orari...</div>
      <div v-else-if="orari.length === 0" class="empty-state">
        <p>Nessun orario disponibile per questa palestra</p>
      </div>
      <div v-else class="orari-list">
        <div v-for="orario in orari" :key="orario.id" class="orario-item">
          <div class="orario-header">
            <h4>{{ orario.gruppoNome }}</h4>
            <span class="day">{{ orario.giornoSettimana }}</span>
          </div>
          <p><strong>Orario:</strong> {{ formatTime(orario.oraInizio) }} - {{ formatTime(orario.oraFine) }}</p>
          <p v-if="orario.numeroCampo"><strong>Campo:</strong> {{ orario.numeroCampo }}</p>
          <p><strong>Posti disponibili:</strong> {{ orario.postiDisponibili }} / {{ orario.capienzaMassima }}</p>
          <button v-if="authStore.isAuthenticated" @click="goToPrenotazione(orario.id)" class="btn-prenota">
            Prenota
          </button>
        </div>
      </div>
    </div>
  </div>
  <div v-else class="loading">Caricamento...</div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { palestraService, orarioService } from '@/services/resourceService'
import { useAuthStore } from '@/stores/authStore'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()

const palestra = ref(null)
const orari = ref([])
const loadingOrari = ref(false)

const formatTime = (time) => {
  if (!time) return ''
  return time.substring(0, 5)
}

const goToPrenotazione = (orarioId) => {
  router.push({
    name: 'Prenotazioni',
    query: { orarioId }
  })
}

onMounted(async () => {
  try {
    const response = await palestraService.getPalestraById(route.params.id)
    palestra.value = response.data
    
    loadingOrari.value = true
    const orariResponse = await orarioService.getOrariByPalestra(route.params.id)
    orari.value = orariResponse.data
  } catch (err) {
    console.error(err)
  } finally {
    loadingOrari.value = false
  }
})
</script>

<style scoped>
.palestra-detail-container {
  padding: 2rem 0;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
}

.header h1 {
  color: #2c3e50;
  margin: 0;
}

.btn-back {
  background-color: #95a5a6;
  color: white;
  padding: 0.5rem 1rem;
  text-decoration: none;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.btn-back:hover {
  background-color: #7f8c8d;
}

.palestra-info {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 2rem;
}

.info-section h3 {
  color: #2c3e50;
  margin-top: 0;
}

.info-section p {
  margin: 0.75rem 0;
  color: #555;
}

.orari-section {
  margin-top: 2rem;
}

.orari-section h3 {
  color: #2c3e50;
  margin-bottom: 1rem;
}

.loading,
.empty-state {
  text-align: center;
  padding: 2rem;
  color: #7f8c8d;
}

.orari-list {
  display: grid;
  gap: 1rem;
}

.orario-item {
  background: white;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.orario-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.orario-header h4 {
  margin: 0;
  color: #2c3e50;
}

.day {
  background-color: #3498db;
  color: white;
  padding: 0.25rem 0.75rem;
  border-radius: 20px;
  font-size: 0.85rem;
}

.orario-item p {
  margin: 0.5rem 0;
  color: #555;
}

.btn-prenota {
  background-color: #27ae60;
  color: white;
  padding: 0.75rem 1.5rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  margin-top: 1rem;
  transition: background-color 0.3s;
}

.btn-prenota:hover {
  background-color: #229954;
}
</style>
