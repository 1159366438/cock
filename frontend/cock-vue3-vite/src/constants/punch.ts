/**
 * 打卡相关常量
 */

// 打卡相关常量
export const PUNCH_CONSTANTS = {
  // 打卡消息
  MESSAGES: {
    SUCCESS: '打卡成功',
    FAILED: '打卡失败！',
    ERROR: '打卡时发生错误',
    NETWORK_ERROR: '网络异常，请稍后重试',
    INVALID_USER: '用户信息无效'
  },
  
  // 打卡状态
  STATUS: {
    PUNCHED: '已打卡',
    UNPUNCHED: '未打卡',
    SUCCESS: '正常',
    LATE: '迟到',
    ABSENT: '缺勤'
  },
  
  // 打卡时间限制（可选）
  TIME_LIMITS: {
    MORNING_START: '08:00',
    MORNING_END: '09:30',
    AFTERNOON_START: '17:00',
    AFTERNOON_END: '18:30'
  }
}

// 页面名称常量
export const PAGE_NAMES = {
  PUNCH: '打卡页面',
  RECORD: '打卡记录',
  USER: '用户中心',
  SETTING: '设置'
}