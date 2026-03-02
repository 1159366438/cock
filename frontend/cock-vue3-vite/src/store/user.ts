/**
 * 用户状态管理
 */
import { defineStore } from 'pinia'
import { userApi } from '../api/punchApi'
import type { UserInfo } from '../types'

export const useUserStore = defineStore('user', {
  state: () => ({
    userInfo: {
      name: '',
      avatar: ''
    } as UserInfo,
    loading: false,
    error: ''
  }),
  
  getters: {
    isLoggedIn: (state) => !!state.userInfo.name
  },
  
  actions: {
    async fetchUserInfo() {
      this.loading = true
      this.error = ''
      try {
        const res = await userApi.getUserInfo()
        if (res.code === 200) {
          this.userInfo = res.data
        } else {
          this.error = '获取用户信息失败'
        }
      } catch (error) {
        this.error = '获取用户信息时发生错误'
        console.error('获取用户信息失败:', error)
      } finally {
        this.loading = false
      }
    }
  }
})