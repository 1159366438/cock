/**
 * 事件常量
 * 管理应用中使用的事件名称常量
 */

export const EVENT_CONSTANTS = {
  // 用户相关事件
  USER: {
    LOGOUT: 'logout',
    LOGIN: 'login',
    UPDATE_PROFILE: 'update-profile',
    CHANGE_PASSWORD: 'change-password',
  },
  
  // 表单相关事件
  FORM: {
    SUBMIT: 'submit',
    RESET: 'reset',
    VALIDATE: 'validate',
    CHANGE: 'change',
  },
  
  // 数据相关事件
  DATA: {
    LOAD: 'load',
    SAVE: 'save',
    DELETE: 'delete',
    REFRESH: 'refresh',
    UPDATE: 'update',
  },
  
  // 页面相关事件
  PAGE: {
    NAVIGATE: 'navigate',
    BACK: 'back',
    FORWARD: 'forward',
  },
  
  // 组件交互事件
  COMPONENT: {
    CLICK: 'click',
    HOVER: 'hover',
    FOCUS: 'focus',
    BLUR: 'blur',
  },
  
  // 系统事件
  SYSTEM: {
    ERROR: 'error',
    SUCCESS: 'success',
    WARNING: 'warning',
    INFO: 'info',
  }
}