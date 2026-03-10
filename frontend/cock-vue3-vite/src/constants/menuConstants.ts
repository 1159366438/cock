/**
 * 菜单相关常量
 * 管理菜单中使用的各种常量值
 */

import { t } from '../locales'

export const MENU_CONSTANTS = {
  // 菜单项文本
  ITEMS: {
    SYSTEM: () => t('menu.system', '员工内网'),
    SYSTEM_NAME: () => t('menu.systemName', '考勤系统'),
    TODAY: () => t('menu.today', '今日打卡'),
    RECORD: () => t('menu.record', '打卡记录'),
    NAVIGATION_TWO: () => t('menu.2', '导航二'),
    NAVIGATION_THREE: () => t('menu.3', '导航三'),
    NAVIGATION_FOUR: () => t('menu.4', '导航四'),
    NAVIGATOR_TWO: () => t('menu.navigatorTwo', 'Navigator Two'),
    NAVIGATOR_THREE: () => t('menu.navigatorThree', 'Navigator Three'),
    NAVIGATOR_FOUR: () => t('menu.navigatorFour', 'Navigator Four'),
  },

  // 获取菜单文本的函数（为了保持兼容性）
  getMenuText: (key: string | number, defaultText: string = '员工内网'): string => {
    const keyStr = String(key);
    switch (keyStr) {
      case 'system': return MENU_CONSTANTS.ITEMS.SYSTEM();
      case 'systemName': return MENU_CONSTANTS.ITEMS.SYSTEM_NAME();
      case 'today': return MENU_CONSTANTS.ITEMS.TODAY();
      case 'record': return MENU_CONSTANTS.ITEMS.RECORD();
      case '2': return MENU_CONSTANTS.ITEMS.NAVIGATION_TWO();
      case '3': return MENU_CONSTANTS.ITEMS.NAVIGATION_THREE();
      case '4': return MENU_CONSTANTS.ITEMS.NAVIGATION_FOUR();
      case 'navigatorTwo': return MENU_CONSTANTS.ITEMS.NAVIGATOR_TWO();
      case 'navigatorThree': return MENU_CONSTANTS.ITEMS.NAVIGATOR_THREE();
      case 'navigatorFour': return MENU_CONSTANTS.ITEMS.NAVIGATOR_FOUR();
      default: return defaultText;
    }
  }
}