/**
 * 路由相关常量
 * 管理应用中使用的各种路由路径和名称
 */

export const ROUTE_CONSTANTS = {
  // 路由路径
  PATHS: {
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
  },

  // 路由名称
  NAMES: {
    // 认证相关页面
    AUTH: {
      LOGIN: 'LoginPage',
    },
    
    // 主要页面
    PAGES: {
      HOME: 'HomePage',
      PUNCH: 'PunchPage',
      RECORD: 'RecordPage',
    },
    
    // 设置相关页面
    SETTINGS: {
      PROFILE: 'ProfilePage',
      ACCOUNT: 'AccountPage',
    },
    
    // 其他页面
    OTHER: {
      NOT_FOUND: 'NotFoundPage',
      ERROR: 'ErrorPage',
    }
  }
}