<template>
  <div class="discipline-container">
    <h2>Discipline Sportive</h2>
    <div class="loading" v-if="loading">Caricamento...</div>
    <div v-else-if="discipline.length === 0" class="empty-state">
      <p>Nessuna disciplina disponibile</p>
    </div>
    <div v-else class="discipline-grid">
      <div v-for="d in discipline" :key="d.id" class="disciplina-card">
        <div class="card-header">
          <h3>{{ d.nome }}</h3>
          <span v-if="d.attiva" class="badge badge-active">Attiva</span>
        </div>
        <div class="card-body">
          <p v-if="d.descrizione" class="descrizione">{{ d.descrizione }}</p>
          <p v-if="d.numGiocatoriMin || d.numGiocatoriMax">
            <strong>Giocatori:</strong> {{ d.numGiocatoriMin || 'N/A' }} - {{ d.numGiocatoriMax || 'N/A' }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { disciplinaService } from '@/services/resourceService'

const discipline = ref([])
const loading = ref(true)

onMounted(async () => {
  try {
    const response = await disciplinaService.getDisciplineAttive()
    discipline.value = response.data
  } catch (err) {
    console.error(err)
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.discipline-container {
  padding: 2rem 0;
}

.discipline-container h2 {
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

.discipline-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 2rem;
}

.disciplina-card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s, box-shadow 0.3s;
}

.disciplina-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.card-header {
  background-color: #9b59b6;
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
  background-color: #27ae60;
}

.card-body {
  padding: 1rem;
}

.descrizione {
  font-style: italic;
  color: #7f8c8d;
  margin: 0.5rem 0;
}

.card-body p {
  color: #2c3e50;
  margin: 0.5rem 0;
}
</style>
