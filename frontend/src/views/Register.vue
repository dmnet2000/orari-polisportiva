<template>
  <div class="register-container">
    <div class="register-box">
      <h2>Registrazione</h2>
      <form @submit.prevent="handleRegister">
        <div class="form-group">
          <label for="username">Username:</label>
          <input
            v-model="form.username"
            type="text"
            id="username"
            required
            class="form-control"
          />
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input
            v-model="form.email"
            type="email"
            id="email"
            required
            class="form-control"
          />
        </div>
        <div class="form-group">
          <label for="firstName">Nome:</label>
          <input
            v-model="form.firstName"
            type="text"
            id="firstName"
            class="form-control"
          />
        </div>
        <div class="form-group">
          <label for="lastName">Cognome:</label>
          <input
            v-model="form.lastName"
            type="text"
            id="lastName"
            class="form-control"
          />
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input
            v-model="form.password"
            type="password"
            id="password"
            required
            class="form-control"
          />
        </div>
        <button type="submit" class="btn-submit" :disabled="loading">
          {{ loading ? 'Registrazione in corso...' : 'Registrati' }}
        </button>
      </form>
      <p v-if="error" class="error-message">{{ error }}</p>
      <p class="login-link">
        Hai già un account?
        <router-link to="/login">Accedi qui</router-link>
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/authStore'

const authStore = useAuthStore()
const router = useRouter()
const form = ref({
  username: '',
  email: '',
  firstName: '',
  lastName: '',
  password: ''
})
const loading = ref(false)
const error = ref('')

const handleRegister = async () => {
  loading.value = true
  error.value = ''
  try {
    await authStore.register(form.value)
    router.push('/login')
  } catch (err) {
    error.value = err.response?.data?.message || 'Errore di registrazione'
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 60vh;
}

.register-box {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 500px;
}

.register-box h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50;
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

.btn-submit {
  width: 100%;
  padding: 0.75rem;
  background-color: #27ae60;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-submit:hover:not(:disabled) {
  background-color: #229954;
}

.btn-submit:disabled {
  background-color: #95a5a6;
  cursor: not-allowed;
}

.error-message {
  color: #e74c3c;
  text-align: center;
  margin-top: 1rem;
}

.login-link {
  text-align: center;
  margin-top: 1rem;
  color: #7f8c8d;
}

.login-link a {
  color: #3498db;
  text-decoration: none;
  font-weight: bold;
}

.login-link a:hover {
  text-decoration: underline;
}
</style>
