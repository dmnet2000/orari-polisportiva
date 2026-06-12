<template>
  <div class="prenotazioni-container">
    <h2>Le Mie Prenotazioni</h2>
    
    <div v-if="authStore.isAuthenticated" class="prenotazioni-content">
      <div v-if="showForm" class="form-section">
        <h3>Nuova Prenotazione</h3>
        <form @submit.prevent="creatPrenotazione">
          <div class="form-group">
            <label for="orario">Seleziona Orario:</label>
            <select v-model="form.orarioId" id="orario" required class="form-control">
              <option value="">-- Seleziona un orario --</option>
              <option v-for="orario in disponibiliOrari" :key="orario.id" :value="orario.id">
                {{ orario.gruppoNome }} - {{ translateDay(orario.giornoSettimana) }} {{ formatTime(orario.oraInizio) }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label for="numeroPosti">Numero Posti:</label>
            <input v-model.number="form.numeroPosti" type="number" id="numeroPosti" min="1" required class="form-control" />
          </div>
          <div class="form-group">
            <label for="note">Note (opzionale):</label>
            <textarea v-model="form.note" id="note" class="form-control"></textarea>
          </div>
          <button type="submit" class="btn-submit" :disabled="loading">{{ loading ? 'Prenotazione...' : 'Prenota' }}</button>
          <button type="button" @click="showForm = false" class="btn-cancel">Annulla</button>
        </form>
        <p v-if="error" class="error-message">{{ error }}</p>
      </div>

      <div v-if="!showForm" class="button-section">
        <button @click="showForm = true" class="btn-new">+ Nuova Prenotazione</button>
      </div>

      <div class="prenotazioni-list">
        <h3>Prenotazioni Attuali</h3>
        <div class="loading" v-if="loadingPrenotazioni">Caricamento...</div>
        <div v-else-if="prenotazioni.length === 0" class="empty-state">
          <p>Non hai ancora prenotazioni</p>
        </div>
        <div v-else class="list">
          <div v-for="prenotazione in prenotazioni" :key="prenotazione.id" class="prenotazione-item">
            <div class="item-header">
              <h4>{{ prenotazione.orarioDescrizione }}</h4>
              <span :class="getStatusClass(prenotazione.stato)">{{ prenotazione.stato }}</span>
            </div>
            <p><strong>Posti prenotati:</strong> {{ prenotazione.numeroPosti }}</p>
            <p v-if="prenotazione.note"><strong>Note:</strong> {{ prenotazione.note }}</p>
            <p><strong>Prenotato il:</strong> {{ formatDate(prenotazione.createdAt) }}</p>
            <button v-if="prenotazione.stato === 'CONFERMATA'" @click="cancelPrenotazione(prenotazione.id)" class="btn-cancel-booking">
              Annulla Prenotazione
            </button>
          </div>
        </div>
      </div>
    </div>
    <div v-else class="not-authenticated">
      <p>Devi essere autenticato per vedere le tue prenotazioni.</p>
      <router-link to="/login" class="btn-login">Accedi</router-link>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { prenotazioneService, orarioService } from '@/services/resourceService'
import { useAuthStore } from '@/stores/authStore'
import { useRoute } from 'vue-router'

const authStore = useAuthStore()
const route = useRoute()

const prenotazioni = ref([])
const disponibiliOrari = ref([])
const showForm = ref(false)
const loading = ref(false)
const loadingPrenotazioni = ref(false)
const error = ref('')

const form = ref({
  orarioId: route.query.orarioId || '',
  numeroPosti: 1,
  note: ''
})

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

const formatDate = (date) => {
  return new Date(date).toLocaleDateString('it-IT')
}

const getStatusClass = (stato) => {
  const classes = 'status-badge '
  switch (stato) {
    case 'CONFERMATA':
      return classes + 'status-confirmed'
    case 'PENDENTE':
      return classes + 'status-pending'
    case 'ANNULLATA':
      return classes + 'status-cancelled'
    case 'COMPLETATA':
      return classes + 'status-completed'
    default:
      return classes
  }
}

const creatPrenotazione = async () => {
  if (!form.value.orarioId) {
    error.value = 'Seleziona un orario'
    return
  }
  loading.value = true
  error.value = ''
  try {
    await prenotazioneService.createPrenotazione({
      orarioId: parseInt(form.value.orarioId),
      utenteId: authStore.user.id,
      numeroPosti: form.value.numeroPosti,
      note: form.value.note
    })
    showForm.value = false
    form.value = { orarioId: '', numeroPosti: 1, note: '' }
    await caricaPrenotazioni()
  } catch (err) {
    error.value = err.response?.data?.message || 'Errore nella prenotazione'
  } finally {
    loading.value = false
  }
}

const cancelPrenotazione = async (id) => {
  if (confirm('Sei sicuro di voler annullare questa prenotazione?')) {
    try {
      await prenotazioneService.cancelPrenotazione(id)
      await caricaPrenotazioni()
    } catch (err) {
      console.error(err)
    }
  }
}

const caricaPrenotazioni = async () => {
  loadingPrenotazioni.value = true
  try {
    const response = await prenotazioneService.getPrenotazioniByUtente(authStore.user.id)
    prenotazioni.value = response.data
  } catch (err) {
    console.error(err)
  } finally {
    loadingPrenotazioni.value = false
  }
}

onMounted(async () => {
  try {
    const response = await orarioService.getOrariAttivi()
    disponibiliOrari.value = response.data.filter(o => o.postiDisponibili > 0)
    await caricaPrenotazioni()
    if (route.query.orarioId) {
      showForm.value = true
    }
  } catch (err) {
    console.error(err)
  }
})
</script>

<style scoped>
.prenotazioni-container {
  padding: 2rem 0;
}

.prenotazioni-container h2 {
  color: #2c3e50;
  font-size: 2rem;
  margin-bottom: 2rem;
}

.not-authenticated {
  text-align: center;
  padding: 3rem;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.btn-login {
  display: inline-block;
  background-color: #3498db;
  color: white;
  padding: 0.75rem 2rem;
  border-radius: 4px;
  text-decoration: none;
  margin-top: 1rem;
}

.form-section {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 2rem;
}

.form-section h3 {
  color: #2c3e50;
  margin-top: 0;
}

.form-group {
  margin-bottom: 1.5rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  color: #2c3e50;
  font-weight: bold;
}

.form-control {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #bdc3c7;
  border-radius: 4px;
  font-size: 1rem;
  box-sizing: border-box;
}

.form-control:focus {
  outline: none;
  border-color: #3498db;
  box-shadow: 0 0 4px rgba(52, 152, 219, 0.3);
}

textarea.form-control {
  resize: vertical;
  min-height: 100px;
}

.btn-submit,
.btn-cancel {
  padding: 0.75rem 1.5rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  margin-right: 0.5rem;
  transition: background-color 0.3s;
}

.btn-submit {
  background-color: #27ae60;
  color: white;
}

.btn-submit:hover:not(:disabled) {
  background-color: #229954;
}

.btn-submit:disabled {
  background-color: #95a5a6;
  cursor: not-allowed;
}

.btn-cancel {
  background-color: #95a5a6;
  color: white;
}

.btn-cancel:hover {
  background-color: #7f8c8d;
}

.error-message {
  color: #e74c3c;
  margin-top: 1rem;
}

.button-section {
  margin-bottom: 2rem;
}

.btn-new {
  background-color: #3498db;
  color: white;
  padding: 0.75rem 2rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  font-size: 1rem;
}

.btn-new:hover {
  background-color: #2980b9;
}

.prenotazioni-list {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.prenotazioni-list h3 {
  color: #2c3e50;
  margin-top: 0;
}

.loading,
.empty-state {
  text-align: center;
  padding: 2rem;
  color: #7f8c8d;
}

.list {
  display: grid;
  gap: 1rem;
}

.prenotazione-item {
  padding: 1rem;
  border: 1px solid #ecf0f1;
  border-radius: 4px;
  background-color: #f8f9fa;
}

.item-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.item-header h4 {
  margin: 0;
  color: #2c3e50;
}

.status-badge {
  padding: 0.25rem 0.75rem;
  border-radius: 4px;
  font-size: 0.85rem;
  font-weight: bold;
  color: white;
}

.status-confirmed {
  background-color: #27ae60;
}

.status-pending {
  background-color: #f39c12;
}

.status-cancelled {
  background-color: #e74c3c;
}

.status-completed {
  background-color: #3498db;
}

.prenotazione-item p {
  margin: 0.5rem 0;
  color: #555;
}

.btn-cancel-booking {
  background-color: #e74c3c;
  color: white;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9rem;
  margin-top: 1rem;
}

.btn-cancel-booking:hover {
  background-color: #c0392b;
}
</style>
