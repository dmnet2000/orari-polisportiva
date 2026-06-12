<template>
  <div id="app" class="app">
    <nav class="navbar">
      <div class="navbar-brand">
        <h1>🏀 Orari Polisportiva</h1>
      </div>
      <div class="navbar-menu">
        <router-link to="/" class="nav-link">Home</router-link>
        <router-link to="/palestre" class="nav-link">Palestre</router-link>
        <router-link to="/discipline" class="nav-link">Discipline</router-link>
        <router-link to="/orari" class="nav-link">Orari</router-link>
        <div v-if="authStore.isAuthenticated" class="auth-menu">
          <router-link to="/prenotazioni" class="nav-link">Le mie prenotazioni</router-link>
          <span class="user-info">{{ authStore.user?.username }}</span>
          <button @click="logout" class="btn-logout">Logout</button>
        </div>
        <div v-else class="auth-menu">
          <router-link to="/login" class="nav-link">Login</router-link>
          <router-link to="/register" class="nav-link">Register</router-link>
        </div>
      </div>
    </nav>
    <main class="main-content">
      <router-view />
    </main>
    <footer class="footer">
      <p>&copy; 2026 Orari Polisportiva. Tutti i diritti riservati.</p>
    </footer>
  </div>
</template>

<script setup>
import { useAuthStore } from '@/stores/authStore'
import { useRouter } from 'vue-router'

const authStore = useAuthStore()
const router = useRouter()

const logout = () => {
  authStore.logout()
  router.push('/login')
}
</script>

<style scoped>
.app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.navbar {
  background-color: #2c3e50;
  color: white;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.navbar-brand h1 {
  margin: 0;
  font-size: 1.5rem;
}

.navbar-menu {
  display: flex;
  gap: 2rem;
  align-items: center;
}

.nav-link {
  color: white;
  text-decoration: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.nav-link:hover,
.nav-link.router-link-active {
  background-color: #3498db;
}

.auth-menu {
  display: flex;
  gap: 1rem;
  align-items: center;
}

.user-info {
  color: #ecf0f1;
}

.btn-logout {
  background-color: #e74c3c;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-logout:hover {
  background-color: #c0392b;
}

.main-content {
  flex: 1;
  padding: 2rem;
  max-width: 1200px;
  width: 100%;
  margin: 0 auto;
}

.footer {
  background-color: #2c3e50;
  color: white;
  text-align: center;
  padding: 2rem;
  margin-top: 2rem;
}

@media (max-width: 768px) {
  .navbar {
    flex-direction: column;
    gap: 1rem;
  }

  .navbar-menu {
    flex-wrap: wrap;
    justify-content: center;
  }
}
</style>
