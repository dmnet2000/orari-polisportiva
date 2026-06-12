import api from './api'

export const palestraService = {
  getAllPalestre: () => api.get('/palestre'),
  getPalestreAttive: () => api.get('/palestre/attive'),
  getPalestraById: (id) => api.get(`/palestre/${id}`),
  createPalestra: (data) => api.post('/palestre', data),
  updatePalestra: (id, data) => api.put(`/palestre/${id}`, data),
  deletePalestra: (id) => api.delete(`/palestre/${id}`)
}

export const disciplinaService = {
  getAllDiscipline: () => api.get('/discipline'),
  getDisciplineAttive: () => api.get('/discipline/attive'),
  getDisciplinaById: (id) => api.get(`/discipline/${id}`),
  createDisciplina: (data) => api.post('/discipline', data),
  updateDisciplina: (id, data) => api.put(`/discipline/${id}`, data),
  deleteDisciplina: (id) => api.delete(`/discipline/${id}`)
}

export const gruppoService = {
  getAllGruppi: () => api.get('/gruppi'),
  getGruppiAttivi: () => api.get('/gruppi/attivi'),
  getGruppiByDisciplina: (disciplinaId) => api.get(`/gruppi/disciplina/${disciplinaId}`),
  getGruppoById: (id) => api.get(`/gruppi/${id}`),
  createGruppo: (data) => api.post('/gruppi', data),
  updateGruppo: (id, data) => api.put(`/gruppi/${id}`, data),
  deleteGruppo: (id) => api.delete(`/gruppi/${id}`)
}

export const orarioService = {
  getAllOrari: () => api.get('/orari'),
  getOrariAttivi: () => api.get('/orari/attivi'),
  getOrariByPalestra: (palestraId) => api.get(`/orari/palestra/${palestraId}`),
  getOrariByGruppo: (gruppoId) => api.get(`/orari/gruppo/${gruppoId}`),
  getOrariByGiorno: (giorno) => api.get(`/orari/giorno/${giorno}`),
  getOrarioById: (id) => api.get(`/orari/${id}`),
  createOrario: (data) => api.post('/orari', data),
  updateOrario: (id, data) => api.put(`/orari/${id}`, data),
  deleteOrario: (id) => api.delete(`/orari/${id}`)
}

export const prenotazioneService = {
  getAllPrenotazioni: () => api.get('/prenotazioni'),
  getPrenotazioniByOrario: (orarioId) => api.get(`/prenotazioni/orario/${orarioId}`),
  getPrenotazioniByUtente: (utenteId) => api.get(`/prenotazioni/utente/${utenteId}`),
  getPrenotazioneById: (id) => api.get(`/prenotazioni/${id}`),
  createPrenotazione: (data) => api.post('/prenotazioni', data),
  updatePrenotazione: (id, data) => api.put(`/prenotazioni/${id}`, data),
  cancelPrenotazione: (id) => api.delete(`/prenotazioni/cancel/${id}`),
  deletePrenotazione: (id) => api.delete(`/prenotazioni/${id}`)
}
