import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/authStore'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/Home.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
    meta: { requiresAuth: false }
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/Register.vue'),
    meta: { requiresAuth: false }
  },
  {
    path: '/palestre',
    name: 'Palestre',
    component: () => import('@/views/Palestre.vue')
  },
  {
    path: '/palestre/:id',
    name: 'PalestraDetail',
    component: () => import('@/views/PalestraDetail.vue')
  },
  {
    path: '/discipline',
    name: 'Discipline',
    component: () => import('@/views/Discipline.vue')
  },
  {
    path: '/orari',
    name: 'Orari',
    component: () => import('@/views/Orari.vue')
  },
  {
    path: '/prenotazioni',
    name: 'Prenotazioni',
    component: () => import('@/views/Prenotazioni.vue'),
    meta: { requiresAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next('/login')
  } else if ((to.path === '/login' || to.path === '/register') && authStore.isAuthenticated) {
    next('/')
  } else {
    next()
  }
})

export default router
