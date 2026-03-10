/**
 * 记录卡片相关常量
 * 管理记录卡片组件中使用的各种常量值
 */

import { t } from '../locales'

export const RECORD_CARD_CONSTANTS = {
  // 表格列标题
  COLUMN_HEADERS: {
    DATE: () => t('record.date', '日期'),
    NAME: () => t('record.name', '姓名'),
    TIME: () => t('record.time', '时间'),
    LOCATION: () => t('record.location', '打卡地点'),
  },

  // 消息文本
  MESSAGES: {
    NO_RECORDS: () => t('messages.noRecordsFound', '暂无打卡记录'),
    USER_PREFIX: () => t('messages.userPrefix', '用户'),
    UNKNOWN_LOCATION: () => t('messages.unknownLocation', '未知地点'),
    UNKNOWN_USER: () => t('messages.unknownUser', '未知用户'),
  }
}