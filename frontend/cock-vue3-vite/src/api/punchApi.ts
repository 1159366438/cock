import service from './axios'
import { ROUTE_PATHS } from '../constants/routes'

// 打卡相关接口封装
export const punchApi = {
  // 获取打卡记录
  getPunchRecords: (params: { userId: string | number; page?: number; size?: number } = { userId: 1 }) => {
    return service.get(ROUTE_PATHS.API.PUNCH.RECORD, { params })
  },
  
  // 打卡接口
  punchIn: (data: { username: string; punchTime: string; userId: string | number }) => {
    return service.post(ROUTE_PATHS.API.PUNCH.IN, data)
  }
}