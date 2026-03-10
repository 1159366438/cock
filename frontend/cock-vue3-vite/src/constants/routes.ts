/**
 * 路由相关常量
 * 管理应用中使用的各种路由路径
 */

export const ROUTE_PATHS = {
  // 认证相关路由
  AUTH: {
    LOGIN: '/login',
    LOGOUT: '/logout',
  },
  
  // 主要页面路由
  PAGES: {
    HOME: '/',
    PUNCH: '/punch',
    RECORD: '/record',
    USER: '/user',
    SETTING: '/setting',
  },
  
  // API 路由
  API: {
    USER: {
      INFO: '/user/info',
      LOGIN: '/user/login',
      LOGOUT: '/user/logout',
    },
    PUNCH: {
      RECORD: '/punch/record',
      IN: '/punch/in',
    }
  }
}