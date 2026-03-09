import { createRouter, createWebHistory } from 'vue-router'
import PunchPage from '../pages/home/PunchPage.vue'
import RecordPage from '../pages/home/RecordPage.vue'
import { PAGE_NAMES } from '../constants/punch'

const routes = [
  {
    path: '/',
    redirect: '/punch'
  },
  {
    path: '/punch',
    name: 'PunchPage',
    component: PunchPage,
    meta: { title: PAGE_NAMES.PUNCH() }
  },
  {
    path: '/record',
    name: 'RecordPage',
    component: RecordPage,
    meta: { title: PAGE_NAMES.RECORD() }
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router