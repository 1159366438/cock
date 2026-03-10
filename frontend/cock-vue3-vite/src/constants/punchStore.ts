/**
 * Punch Store状态常量
 * 管理打卡状态存储中的初始值和默认值常量
 */

export const PUNCH_STORE_CONSTANTS = {
  // 初始状态值
  INITIAL_STATE: {
    IS_PUNCHED: false,
    PUNCHED_TIME: '',
    LOADING: false,
    ERROR: '',
  },
  
  // 分页初始值
  PAGINATION: {
    TOTAL: 0,
    PAGE: 1,
    PAGES: 0,
  },
  
  // 默认参数值
  DEFAULT_PARAMS: {
    USER_ID: 1,
    PAGE: 1,
  },
  
  // 默认回退值
  FALLBACK_VALUES: {
    TOTAL: 0,
    PAGE: 1,
    PAGES: 0,
  }
}