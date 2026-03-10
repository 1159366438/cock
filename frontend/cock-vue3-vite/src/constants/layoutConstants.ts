/**
 * 布局相关常量
 * 管理应用中使用的布局和菜单相关常量
 */

import { t } from '../locales'

export const LAYOUT_CONSTANTS = {
  // 消息文本
  MESSAGES: {
    LOGOUT_ERROR: () => t('messages.logoutError', '登出过程中出现错误'),
  },
  
  // 菜单项索引
  MENU_INDEXES: {
    DEFAULT_OPENED: ['1'],  // 默认展开的菜单
    SUB_MENU_SYSTEM: '1',   // 系统子菜单
    NAVIGATION_TWO: '2',    // 导航项2
    NAVIGATION_THREE: '3',  // 导航项3
    NAVIGATION_FOUR: '4',   // 导航项4
    PUNCH: 'punch',         // 打卡页面
    RECORD: 'record',       // 记录页面
  },
  
  // 导航项文本
  NAVIGATION: {
    TWO: 'navigatorTwo',
    THREE: 'navigatorThree',
    FOUR: 'navigatorFour',
  },
  
  // 菜单配置
  MENU_CONFIG: {
    ACTIVE_TEXT_COLOR: '#409EFF',
    BACKGROUND_COLOR: '#545c64',
    TEXT_COLOR: '#fff',
  }
}