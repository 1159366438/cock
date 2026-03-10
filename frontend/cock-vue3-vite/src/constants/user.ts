/**
 * 用户相关常量
 * 管理用户模块中使用的各种常量值
 */

export const USER_CONSTANTS = {
  // 默认用户值
  DEFAULT_VALUES: {
    USER_ID: 1,  // 默认用户ID
    USERNAME: 'guest',  // 默认用户名
    AVATAR: '',  // 默认头像
  },

  // 用户验证规则
  VALIDATION: {
    MIN_USERNAME_LENGTH: 3,  // 最小用户名长度
    MAX_USERNAME_LENGTH: 50,  // 最大用户名长度
    MIN_PASSWORD_LENGTH: 6,  // 最小密码长度
    MAX_PASSWORD_LENGTH: 128,  // 最大密码长度
  },

  // 本地存储键名
  STORAGE_KEYS: {
    IS_LOGGED_IN: 'isLoggedIn',
    AUTH_TOKEN: 'authToken',
    REMEMBERED_USERNAME: 'rememberedUsername',
  }
}