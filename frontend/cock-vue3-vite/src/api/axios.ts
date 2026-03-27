/**
 * Axios 实例配置
 * 配置API请求实例、拦截器和错误处理
 * @author Attendance System Team
 * @since 2026-03-15
 * @version v1.1.0-alpha.1
 */
import axios from 'axios'
import { APP_CONFIG } from '../config/appConfig'
import { STATUS_CODES, API_ERROR_MESSAGES, MESSAGE_CONSTANTS } from '../constants'
import { ElMessage } from 'element-plus'
import { APP_CONSTANTS } from '../constants'

// 创建 axios 实例
const service = axios.create({
  baseURL: '/api', // 基础URL
  timeout: APP_CONFIG.API.TIMEOUT, // 请求超时时间
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 从localStorage获取JWT Token
    const token = localStorage.getItem(APP_CONSTANTS.USER.STORAGE_KEYS.AUTH_TOKEN)
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  error => {
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    // 检查响应是否存在
    if (!response) {
      console.error('响应为空')
      return Promise.reject(new Error(MESSAGE_CONSTANTS.COMMON.UNKNOWN_ERROR()))
    }
    
    // 直接返回响应数据
    const res = response.data

    if (res && res.code ===  STATUS_CODES.BUSINESS.SUCCESS) {
      // 成功响应，直接返回
      return res
    } else {
      // 业务错误处理 - 仅显示错误消息，具体业务逻辑由调用方处理
      console.error('业务错误:', res ? (res.msg || res.message) : MESSAGE_CONSTANTS.COMMON.UNKNOWN_ERROR())
      
      // 统一错误提示
      ElMessage.error(res ? (res.msg || res.message || MESSAGE_CONSTANTS.COMMON.UNKNOWN_ERROR()) : MESSAGE_CONSTANTS.COMMON.UNKNOWN_ERROR())
      
      return response
    }
  },
  error => {
    console.error('网络错误:', error)
    
    // 根据错误类型提供不同提示
    let errorMessage = API_ERROR_MESSAGES.NETWORK.REQUEST_FAILED()
    if (error.response) {
      // 服务器响应了错误状态码
      const status = error.response.status
      // 根据状态码选择相应的错误消息
      switch(status) {
        case 400:
          errorMessage = API_ERROR_MESSAGES.HTTP.BAD_REQUEST()
          break
        case 401:
          errorMessage = API_ERROR_MESSAGES.HTTP.UNAUTHORIZED()
          break
        case 403:
          errorMessage = API_ERROR_MESSAGES.HTTP.FORBIDDEN()
          break
        case 404:
          errorMessage = API_ERROR_MESSAGES.HTTP.NOT_FOUND()
          break
        case 500:
          errorMessage = API_ERROR_MESSAGES.HTTP.SERVER_ERROR()
          break
        default:
          errorMessage = API_ERROR_MESSAGES.HTTP.DEFAULT_ERROR(status)
      }
      
      // 特殊处理401未授权错误
      if (status === 401) {
        // 清除本地认证信息
        localStorage.removeItem('token')
        localStorage.removeItem(APP_CONSTANTS.USER.STORAGE_KEYS.AUTH_TOKEN)
        localStorage.removeItem(APP_CONSTANTS.USER.STORAGE_KEYS.IS_LOGGED_IN)
        
        // 显示错误消息
        ElMessage.error('认证失效，请重新登录')
        
        // 跳转到登录页
        window.location.href = '/login'
        
        return Promise.reject(error)
      }
      
      // 特殊处理403禁止访问错误
      if (status === 403) {
        // 检查是否是因为认证信息过期或无效导致的403
        const token = localStorage.getItem(APP_CONSTANTS.USER.STORAGE_KEYS.AUTH_TOKEN)
        if (!token) {
          // 如果没有token却收到403，可能是认证状态不一致
          localStorage.removeItem(APP_CONSTANTS.USER.STORAGE_KEYS.IS_LOGGED_IN)
          
          // 显示错误消息
          ElMessage.error('访问被拒绝，请重新登录')
          
          // 跳转到登录页
          window.location.href = '/login'
        } else {
          // 如果有token但仍然收到403，可能是权限不足
          ElMessage.error('您没有权限访问该资源')
        }
        
        return Promise.reject(error)
      }
    } else if (error.request) {
      // 请求已发出但没有收到响应
      errorMessage = API_ERROR_MESSAGES.NETWORK.SERVER_UNAVAILABLE()
    }
    
    // 显示错误消息
    ElMessage.error(errorMessage)
    
    // 返回错误以便调用方处理
    return Promise.reject(error)
  }
)

export default service