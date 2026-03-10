import service from './axios'
import { ROUTE_PATHS } from '../constants/routes'

// 用户相关接口封装
export const userApi = {
  // 获取用户信息（模拟接口）
  getUserInfo: () => {
    return service.get(ROUTE_PATHS.API.USER.INFO)
  },
  
  // 用户登录
  login: (username: string, password: string) => {
    return service.post(ROUTE_PATHS.API.USER.LOGIN, {
      username,
      password
    })
  },
  
  // 用户登出
  logout: () => {
    return service.post(ROUTE_PATHS.API.USER.LOGOUT)
  }
}