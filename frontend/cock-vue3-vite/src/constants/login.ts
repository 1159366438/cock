/**
 * 登录相关常量
 * 管理登录页面中使用的各种常量值
 */

import { t } from '../locales'

export const LOGIN_CONSTANTS = {
  // 登录页面文本
  TEXTS: {
    TITLE: () => t('login.title', '用户登录'),
    REGISTER_TITLE: () => t('login.registerTitle', '用户注册'),
    USERNAME_LABEL: () => t('login.usernameLabel', '用户名'),
    PASSWORD_LABEL: () => t('login.passwordLabel', '密码'),
    CONFIRM_PASSWORD_LABEL: () => t('login.confirmPasswordLabel', '确认密码'),
    AGE_LABEL: () => t('login.ageLabel', '年龄'),
    AVATAR_LABEL: () => t('login.avatarLabel', '头像'),
    USERNAME_PLACEHOLDER: () => t('login.usernamePlaceholder', '请输入用户名'),
    PASSWORD_PLACEHOLDER: () => t('login.passwordPlaceholder', '请输入密码'),
    CONFIRM_PASSWORD_PLACEHOLDER: () => t('login.confirmPasswordPlaceholder', '请再次输入密码'),
    AGE_PLACEHOLDER: () => t('login.agePlaceholder', '请输入年龄'),
    AVATAR_PLACEHOLDER: () => t('login.avatarPlaceholder', '请输入头像URL'),
    REMEMBER_ME: () => t('login.rememberMe', '记住密码'),
    ACCEPT_TERMS: () => t('login.acceptTerms', '我已阅读并同意用户协议'),
    BUTTON: () => t('login.button', '登录'),
    REGISTER_BUTTON: () => t('login.registerButton', '注册'),
    FORGOT_PASSWORD: () => t('login.forgotPassword', '忘记密码'),
    BACK_TO_LOGIN: () => t('login.backToLogin', '返回登录'),
    REGISTER: () => t('login.register', '注册账户'),
  },

  // 登录验证消息
  VALIDATION_MESSAGES: {
    USERNAME_REQUIRED: () => t('login.usernameRequired', '请输入用户名'),
    USERNAME_LENGTH: () => t('login.usernameLength', '用户名长度至少3位'),
    PASSWORD_REQUIRED: () => t('login.passwordRequired', '请输入密码'),
    PASSWORD_LENGTH: () => t('login.passwordLength', '密码长度至少6位'),
    CONFIRM_PASSWORD_REQUIRED: () => t('login.confirmPasswordRequired', '请确认密码'),
    PASSWORD_MISMATCH: () => t('login.passwordMismatch', '两次输入的密码不一致'),
    AGE_RANGE: () => t('login.ageRange', '年龄应在1-120之间'),
    TERMS_NOT_ACCEPTED: () => t('login.termsNotAccepted', '请阅读并同意用户协议'),
    REGISTRATION_FAILED: () => t('login.registrationFailed', '注册失败'),
    VALIDATION_FAILED: () => t('login.validationFailed', '请检查输入信息'),
  },

  // 辅助消息
  HELP_MESSAGES: {
    FORGOT_PASSWORD_TIP: () => t('login.forgotPasswordTip', '请联系管理员重置密码'),
    REGISTER_TIP: () => t('login.registerTip', '请联系管理员开通账户'),
  },
  
  // 特性标志
  FEATURE_FLAGS: {
    SHOW_AGE_FIELD: () => true,
    SHOW_AVATAR_FIELD: () => true,
  }
}