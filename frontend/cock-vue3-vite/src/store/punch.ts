/**
 * 打卡状态管理
 */
import { defineStore } from 'pinia'
import { punchApi } from '../api/punchApi'
import type { PunchRecord } from '../types'

export const usePunchStore = defineStore('punch', {
  state: () => ({
    isPunched: false,
    punchedTime: '',
    punchRecords: [] as PunchRecord[],
    loading: false,
    error: ''
  }),
  
  getters: {
    hasPunchRecords: (state) => state.punchRecords.length > 0
  },
  
  actions: {
    async punchIn() {
      this.loading = true
      this.error = ''
      try {
        const res = await punchApi.getPunchRecords()
        if (res.status === 200) {
          console.log('打卡记录:', res);
          this.isPunched = true
          const now = new Date()
          this.punchedTime = now.toLocaleTimeString('zh-CN', {
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
          })
          return true
        } else {
          this.error = '打卡失败'
          return false
        }
      } catch (error) {
        this.error = '打卡时发生错误'
        console.error('打卡接口异常:', error)
        return false
      } finally {
        this.loading = false
      }
    },
    
    async fetchPunchRecords() {
      this.loading = true
      this.error = ''
      try {
        const res = await punchApi.getPunchRecords()
        if (res.status === 200) {
          this.punchRecords = res.data
        } else {
          this.error = '获取打卡记录失败'
        }
      } catch (error) {
        this.error = '获取打卡记录时发生错误'
        console.error('获取打卡记录失败:', error)
      } finally {
        this.loading = false
      }
    }
  }
})