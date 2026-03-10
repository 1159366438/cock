/**
 * 用户状态管理
 */
import { defineStore } from 'pinia'
import { userApi } from '../../api/userApi'
import type { UserInfo } from '../../types'
import { USER_CONSTANTS } from '../../constants/userConstants'
import { STATUS_CODES } from '../../constants/statusCodes'
import { BOOLEAN_CONSTANTS } from '../../constants/booleans'
import { MESSAGE_CONSTANTS } from '../../constants/messages'
import { STORE_NAMES } from '../../constants/appArchitectureConstants'
// import { t } from '../../locales'  // 移除未使用的导入

export const useUserStore = defineStore(STORE_NAMES.USER, {
  state: () => ({
    userInfo: {
      name: '',
      avatar: '',
      userId: USER_CONSTANTS.DEFAULT_VALUES.USER_ID // 默认用户ID
    } as UserInfo,
    loading: BOOLEAN_CONSTANTS.FALSE,
    error: ''
  }),
  
  getters: {
    isLoggedIn: (state) => !!state.userInfo.name
  },
  
  actions: {
    async fetchUserInfo() {
      this.loading = BOOLEAN_CONSTANTS.TRUE
      this.error = ''
      try {
        const res = await userApi.getUserInfo()
        // 开发调试时可以启用日志
        console.log('获取用户信息接口响应:', res)
        if (res.status === STATUS_CODES.BUSINESS.SUCCESS) {
          this.userInfo.name = res.data.username
          this.userInfo.userId = res.data.id || USER_CONSTANTS.DEFAULT_VALUES.USER_ID // 使用后端返回的用户ID，默认1
        } else {
          this.error = MESSAGE_CONSTANTS.USER_INFO.FETCH_FAILED()
        }
      } catch (error) {
        this.error = MESSAGE_CONSTANTS.USER_INFO.FETCH_ERROR()
        console.error('获取用户信息失败:', error)
      } finally {
        this.loading = BOOLEAN_CONSTANTS.FALSE
      }
    },
    
    async login(username: string, _password: string) {
      this.loading = BOOLEAN_CONSTANTS.TRUE
      this.error = ''
      try {
        // TODO: 实现真实的登录API调用
        // const res = await userApi.login(username, password)
        
        // 临时模拟登录成功
        // 如果登录成功，更新用户信息
        this.userInfo.name = username
        this.userInfo.userId = USER_CONSTANTS.DEFAULT_VALUES.USER_ID
        
        return {
          success: BOOLEAN_CONSTANTS.TRUE,
          message: MESSAGE_CONSTANTS.USER_INFO.LOGIN_SUCCESS(),
          data: { username, userId: USER_CONSTANTS.DEFAULT_VALUES.USER_ID }
        }
      } catch (error) {
        console.error('登录失败:', error)
        this.error = MESSAGE_CONSTANTS.USER_INFO.LOGIN_FAILED()
        return {
          success: BOOLEAN_CONSTANTS.FALSE,
          message: MESSAGE_CONSTANTS.USER_INFO.LOGIN_FAILED(),
          error
        }
      } finally {
        this.loading = BOOLEAN_CONSTANTS.FALSE
      }
    },
    
    async logout() {
      try {
        // TODO: 实现真实的登出API调用
        // await userApi.logout()
        
        // 清空本地用户信息
        this.userInfo = {
          name: '',
          avatar: '',
          userId: USER_CONSTANTS.DEFAULT_VALUES.USER_ID
        }
        
        // 清除认证状态
        localStorage.removeItem(USER_CONSTANTS.STORAGE_KEYS.IS_LOGGED_IN)
        localStorage.removeItem(USER_CONSTANTS.STORAGE_KEYS.AUTH_TOKEN)
        localStorage.removeItem(USER_CONSTANTS.STORAGE_KEYS.REMEMBERED_USERNAME)
        
        return {
          success: true,
          message: MESSAGE_CONSTANTS.USER_INFO.LOGOUT_SUCCESS()
        }
      } catch (error) {
        console.error('登出失败:', error)
        return {
          success: false,
          message: MESSAGE_CONSTANTS.USER_INFO.LOGOUT_FAILED(),
          error
        }
      }
    }
  }
})