/**
 * 类型定义文件
 * 统一管理项目中所有TypeScript类型
 */

// 用户信息类型
export interface UserInfo {
  name: string // 用户名
  avatar: string // 用户头像URL
}

// 打卡记录类型
export interface PunchRecord {
  date: string // 打卡日期
  time: string // 打卡时间
  status: string // 打卡状态
}

// 菜单相关类型
export type MenuKey = string
export type MenuText = string

// API响应类型
export interface ApiResponse<T = any> {
  code: number
  data: T
  message?: string
}