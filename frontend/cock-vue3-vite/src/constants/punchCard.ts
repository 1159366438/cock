/**
 * 打卡卡片相关常量
 * 管理打卡卡片组件中使用的各种常量值
 */

import { t } from '../locales'

export const PUNCH_CARD_CONSTANTS = {
  // 文本内容
  TEXTS: {
    TODAY: () => t('menu.today', '今日打卡'),
    PUNCH_NOW: () => t('buttons.punchNow', '立即打卡'),
    ALREADY_PUNCHED: () => t('buttons.alreadyPunched', '已打卡'),
  },

  // 打卡状态文本
  STATUS: {
    PUNCHED: () => t('punchStatus.punched', '今日已打卡'),
    UNPUNCHED: () => t('punchStatus.unpunched', '今日未打卡'),
  },

  // 消息文本
  MESSAGES: {
    UNKNOWN_USER: () => t('messages.unknownUser', '未知用户'),
  }
}