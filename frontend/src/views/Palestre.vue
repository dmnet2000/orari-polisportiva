<template>
  <div class="palestre-container">
    <h2>Palestre</h2>
    <div class="loading" v-if="loading">Caricamento...</div>
    <div v-else-if="palestre.length === 0" class="empty-state">
      <p>Nessuna palestra disponibile</p>
    </div>
    <div v-else class="palestre-grid">
      <div v-for="palestra in palestre" :key="palestra.id" class="palestra-card">
        <div class="card-header">
          <h3>{{ palestra.nome }}</h3>
          <span v-if="palestra.attiva" class="badge badge-active">Attiva</span>
          <span v-else class="badge badge-inactive">Inattiva</span>
        </div>
        <div class="card-body">
          <p><strong>Indirizzo:</strong> {{ palestra.indirizzo }}</p>
          <p v-if="palestra.citta"><strong>Città:</strong> {{ palestra.citta }}</p>
          <p v-if="palestra.telefono"><strong>Telefono:</strong> {{ palestra.telefono }}</p>
          <p v-if="palestra.numCampi"><strong>Campi:</strong> {{ palestra.numCampi }}</p>
          <p v-if="palestra.descrizione" class="descrizione">{{ palestra.descrizione }}</p>
        </div>
        <div class="card-footer">
          <router-link :to="`/palestre/${palestra.id}`" class="btn btn-primary">Visualizza</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { palestraService } from '@/services/resourceService'

const palestre = ref([])
const loading = ref(true)
const error = ref('')

onMounted(async () => {
  try {
    const response = await palestraService.getPalestreAttive()
    palestre.value = response.data
  } catch (err) {
    error.value = 'Errore nel caricamento delle palestre'
    console.error(err)
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.palestre-container {
  padding: 2rem 0;
}

.palestre-container h2 {
  margin-bottom: 2rem;
  color: #2c3e50;
  font-size: 2rem;
}

.loading,
.empty-state {
  text-align: center;
  padding: 2rem;
  color: #7f8c8d;
}

.palestre-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 2rem;
}

.palestra-card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s, box-shadow 0.3s;
}

.palestra-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.card-header {
  background-color: #3498db;
  color: white;
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-header h3 {
  margin: 0;
}

.badge {
  padding: 0.25rem 0.75rem;
  border-radius: 20px;
  font-size: 0.75rem;
  font-weight: bold;
}

.badge-active {
  background-color: #27ae60;
}

.badge-inactive {
  background-color: #e74c3c;
}

.card-body {
  padding: 1rem;
}

.card-body p {
  margin: 0.5rem 0;
  color: #2c3e50;
}

.descrizione {
  font-style: italic;
  color: #7f8c8d;
  margin-top: 1rem;
  padding-top: 1rem;
  border-top: 1px solid #ecf0f1;
}

.card-footer {
  padding: 1rem;
  background-color: #f8f9fa;
  border-top: 1px solid #ecf0f1;
}

.btn {
  display: inline-block;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  text-decoration: none;
  font-weight: bold;
  transition: all 0.3s;
}

.btn-primary {
  background-color: #3498db;
  color: white;
}

.btn-primary:hover {
  background-color: #2980b9;
}
</style>
