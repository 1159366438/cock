/**
 * 日期格式常量
 * 管理应用中使用的日期格式常量
 */

export const DATE_FORMAT_CONSTANTS = {
  // 基础日期格式
  BASIC: {
    DATE: 'date',           // 'YYYY-MM-DD'
    TIME: 'time',           // 'HH:mm:ss'
    DATETIME: 'datetime',   // 'YYYY-MM-DD HH:mm:ss'
  },
  
  // 详细日期格式
  DETAILED: {
    DATE_FULL: 'YYYY-MM-DD',
    TIME_FULL: 'HH:mm:ss',
    DATETIME_FULL: 'YYYY-MM-DD HH:mm:ss',
    DATE_CHINESE: 'YYYY年MM月DD日',
    DATETIME_CHINESE: 'YYYY年MM月DD日 HH:mm:ss',
  },
  
  // 特定用途格式
  SPECIAL: {
    YEAR_MONTH: 'YYYY-MM',
    MONTH_DAY: 'MM-DD',
    HOUR_MINUTE: 'HH:mm',
  }
}