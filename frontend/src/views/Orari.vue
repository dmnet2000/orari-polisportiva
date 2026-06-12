<template>
  <div class="orari-container">
    <h2>Orari dei Corsi</h2>
    <div class="filters">
      <select v-model="filterGiorno" class="filter-select">
        <option value="">Tutti i giorni</option>
        <option value="MONDAY">Lunedì</option>
        <option value="TUESDAY">Martedì</option>
        <option value="WEDNESDAY">Mercoledì</option>
        <option value="THURSDAY">Giovedì</option>
        <option value="FRIDAY">Venerdì</option>
        <option value="SATURDAY">Sabato</option>
        <option value="SUNDAY">Domenica</option>
      </select>
    </div>
    <div class="loading" v-if="loading">Caricamento...</div>
    <div v-else-if="filteredOrari.length === 0" class="empty-state">
      <p>Nessun orario disponibile</p>
    </div>
    <div v-else class="orari-list">
      <div v-for="orario in filteredOrari" :key="orario.id" class="orario-card">
        <div class="card-header">
          <h3>{{ orario.gruppoNome }}</h3>
          <span class="day-badge">{{ translateDay(orario.giornoSettimana) }}</span>
        </div>
        <div class="card-body">
          <p><strong>Palestra:</strong> {{ orario.palestraNome }}</p>
          <p><strong>Orario:</strong> {{ formatTime(orario.oraInizio) }} - {{ formatTime(orario.oraFine) }}</p>
          <p v-if="orario.numeroCampo"><strong>Campo:</strong> {{ orario.numeroCampo }}</p>
          <p><strong>Posti disponibili:</strong> <span :class="getAvailabilityClass(orario)">{{ orario.postiDisponibili }} / {{ orario.capienzaMassima }}</span></p>
        </div>
        <div class="card-footer">
          <button v-if="authStore.isAuthenticated" @click="prenota(orario)" :disabled="orario.postiDisponibili === 0" class="btn-prenota">
            {{ orario.postiDisponibili === 0 ? 'Esaurito' : 'Prenota' }}
          </button>
          <router-link v-else to="/login" class="btn-prenota">Accedi per prenotare</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { orarioService } from '@/services/resourceService'
import { useAuthStore } from '@/stores/authStore'

const authStore = useAuthStore()
const router = useRouter()

const orari = ref([])
const loading = ref(true)
const filterGiorno = ref('')

const translateDay = (day) => {
  const days = {
    'MONDAY': 'Lunedì',
    'TUESDAY': 'Martedì',
    'WEDNESDAY': 'Mercoledì',
    'THURSDAY': 'Giovedì',
    'FRIDAY': 'Venerdì',
    'SATURDAY': 'Sabato',
    'SUNDAY': 'Domenica'
  }
  return days[day] || day
}

const formatTime = (time) => {
  if (!time) return ''
  return time.substring(0, 5)
}

const getAvailabilityClass = (orario) => {
  if (orario.postiDisponibili === 0) return 'availability-none'
  if (orario.postiDisponibili < 3) return 'availability-low'
  return 'availability-good'
}

const filteredOrari = computed(() => {
  if (!filterGiorno.value) return orari.value
  return orari.value.filter(o => o.giornoSettimana === filterGiorno.value)
})

const prenota = (orario) => {
  router.push({
    name: 'Prenotazioni',
    query: { orarioId: orario.id }
  })
}

onMounted(async () => {
  try {
    const response = await orarioService.getAllOrari()
    orari.value = response.data.filter(o => o.attivo)
  } catch (err) {
    console.error(err)
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.orari-container {
  padding: 2rem 0;
}

.orari-container h2 {
  margin-bottom: 2rem;
  color: #2c3e50;
  font-size: 2rem;
}

.filters {
  margin-bottom: 2rem;
}

.filter-select {
  padding: 0.75rem;
  border: 1px solid #bdc3c7;
  border-radius: 4px;
  font-size: 1rem;
}

.loading,
.empty-state {
  text-align: center;
  padding: 2rem;
  color: #7f8c8d;
}

.orari-list {
  display: grid;
  gap: 1.5rem;
}

.orario-card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s, box-shadow 0.3s;
}

.orario-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.card-header {
  background-color: #f39c12;
  color: white;
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-header h3 {
  margin: 0;
}

.day-badge {
  background-color: rgba(0, 0, 0, 0.2);
  padding: 0.25rem 0.75rem;
  border-radius: 20px;
  font-size: 0.85rem;
}

.card-body {
  padding: 1rem;
}

.card-body p {
  margin: 0.5rem 0;
  color: #2c3e50;
}

.availability-good {
  color: #27ae60;
  font-weight: bold;
}

.availability-low {
  color: #f39c12;
  font-weight: bold;
}

.availability-none {
  color: #e74c3c;
  font-weight: bold;
}

.card-footer {
  padding: 1rem;
  background-color: #f8f9fa;
  border-top: 1px solid #ecf0f1;
}

.btn-prenota {
  background-color: #3498db;
  color: white;
  padding: 0.75rem 1.5rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  text-decoration: none;
  display: inline-block;
  transition: background-color 0.3s;
}

.btn-prenota:hover:not(:disabled) {
  background-color: #2980b9;
}

.btn-prenota:disabled {
  background-color: #95a5a6;
  cursor: not-allowed;
}
</style>
