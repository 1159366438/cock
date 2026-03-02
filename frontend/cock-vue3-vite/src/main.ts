import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'

import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue' // 导入所有图标
import axios from 'axios'
import router from './router'
import ElementPlus from 'element-plus' // 导入 Element Plus
import 'element-plus/dist/index.css' // 导入样式

const app = createApp(App)
const pinia = createPinia()

// 全局注册 Element Plus 图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

// Axios 配置
//axios.defaults.baseURL = '/api'
app.config.globalProperties.$axios = axios

app.use(ElementPlus)
app.use(router)
app.use(pinia)
app.mount('#app')