import axios from 'axios'

// 打卡相关接口封装
export const punchApi = {
  // 获取打卡记录
  getPunchRecords: () => {
    return axios.get('/api/punch/record')
  },
  
  // 打卡接口
  punchIn: (data: { username: string; punchTime: string }) => {
    return axios.post('/api/punch/in', data)
  }
}