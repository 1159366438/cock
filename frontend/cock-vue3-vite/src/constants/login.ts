/**
 * 登录相关常量
 * 管理登录页面中使用的各种常量值
 */

import { t } from '../locales'

export const LOGIN_CONSTANTS = {
  // 登录页面文本
  TEXTS: {
    TITLE: () => t('login.title', '用户登录'),
    USERNAME_LABEL: () => t('login.usernameLabel', '用户名'),
    PASSWORD_LABEL: () => t('login.passwordLabel', '密码'),
    USERNAME_PLACEHOLDER: () => t('login.usernamePlaceholder', '请输入用户名'),
    PASSWORD_PLACEHOLDER: () => t('login.passwordPlaceholder', '请输入密码'),
    REMEMBER_ME: () => t('login.rememberMe', '记住密码'),
    BUTTON: () => t('login.button', '登录'),
    FORGOT_PASSWORD: () => t('login.forgotPassword', '忘记密码'),
    REGISTER: () => t('login.register', '注册账户'),
  },

  // 登录验证消息
  VALIDATION_MESSAGES: {
    USERNAME_REQUIRED: () => t('login.usernameRequired', '请输入用户名'),
    USERNAME_LENGTH: () => t('login.usernameLength', '用户名长度至少3位'),
    PASSWORD_REQUIRED: () => t('login.passwordRequired', '请输入密码'),
    PASSWORD_LENGTH: () => t('login.passwordLength', '密码长度至少6位'),
    VALIDATION_FAILED: () => t('login.validationFailed', '请检查输入信息'),
  },

  // 辅助消息
  HELP_MESSAGES: {
    FORGOT_PASSWORD_TIP: () => t('login.forgotPasswordTip', '请联系管理员重置密码'),
    REGISTER_TIP: () => t('login.registerTip', '请联系管理员开通账户'),
  }
}