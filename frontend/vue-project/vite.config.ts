import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueJsx(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  server: {
  proxy: {
    // 代理 /api 到 Spring Boot 后端
    '/api': {
      target: 'http://localhost:8080/api',
      changeOrigin: true,
      rewrite: (path) => path.replace(/^\/api/, '')
    },
    // 代理 /other 到另一个后端服务
    '/other': {
      target: 'https://other-server.com',
      changeOrigin: true,
      secure: false // 忽略 HTTPS 证书错误（开发环境用）
    }
  }
}
})


