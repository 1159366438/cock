/**
 * 认证相关API接口封装
 * 提供与用户认证相关的所有后端接口调用方法
 */

import service from './axios'
import { APP_CONSTANTS } from '../constants'

// 认证相关接口封装
export const authApi = {
  /**
   * 用户登录
   * @param username - 用户名
   * @param password - 密码
   * @returns Promise - 包含JWT Token和用户信息的响应
   */
  login: (username: string, password: string) => {
    return service.post(APP_CONSTANTS.ROUTE.PATHS.API.USER.LOGIN, {
      username,
      password
    })
  },

  /**
   * 用户登出
   * @returns Promise - 登出结果响应
   */
  logout: () => {
    // 从localStorage获取JWT Token
    const token = localStorage.getItem(APP_CONSTANTS.USER.STORAGE_KEYS.AUTH_TOKEN)
    
    // 如果有token，则调用后端登出接口
    if (token) {
      return service.post(APP_CONSTANTS.ROUTE.PATHS.API.USER.LOGOUT)
    }
    
    // 如果没有token，直接返回成功状态
    return Promise.resolve({ data: { code: 200, msg: 'Logged out successfully', data: {} } })
  },

  /**
   * 用户注册
   * @param username - 用户名
   * @param password - 密码
   * @param confirmPassword - 确认密码
   * @param age - 年龄（可选）
   * @param avatar - 头像（可选）
   * @param gender - 性别（可选）
   * @returns Promise - 注册结果响应
   */
  register: (username: string, password: string, confirmPassword: string, age?: number, avatar?: string, gender?: number) => {
    return service.post('/api/auth/register', {
      username,        // 用户名
      password,        // 密码
      confirmPassword, // 确认密码
      age,             // 年龄
      avatar,          // 头像
      gender           // 性别
    })
  }
}