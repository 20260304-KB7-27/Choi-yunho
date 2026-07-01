import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import AuthRoutes from './auth.js'
import GalleryRoutes from './gallery.js'
import TravelRoutes from './travel.js'
import BoardRoutes from './board.js'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'home', component: () => import('../pages/HomePage.vue') },
    ...AuthRoutes,
    ...GalleryRoutes,
    ...TravelRoutes,
    ...BoardRoutes,
  ],
})

export default router
