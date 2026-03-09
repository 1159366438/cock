/**
 * 用户状态管理
 */
import { defineStore } from 'pinia'
import { userApi } from '../../api/userApi'
import type { UserInfo } from '../../types'
import { BUSINESS_STATUS } from '../../constants/api'
import { MESSAGE_CONSTANTS } from '../../constants/messages'
import { t } from '../../locales'

export const useUserStore = defineStore('user', {
  state: () => ({
    userInfo: {
      name: '',
      avatar: '',
      userId: 1 // 默认用户ID
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
        // 开发调试时可以启用日志
        console.log('获取用户信息接口响应:', res)
        if (res.status === BUSINESS_STATUS.SUCCESS) {
          this.userInfo.name = res.data.username
          this.userInfo.userId = res.data.id || 1 // 使用后端返回的用户ID，默认1
        } else {
          this.error = MESSAGE_CONSTANTS.USER_INFO.FETCH_FAILED()
        }
      } catch (error) {
        this.error = MESSAGE_CONSTANTS.USER_INFO.FETCH_ERROR()
        console.error('获取用户信息失败:', error)
      } finally {
        this.loading = false
      }
    }
  }
})