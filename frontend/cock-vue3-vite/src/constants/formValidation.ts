/**
 * 表单验证相关常量
 * 管理应用中使用的表单验证相关常量
 */

export const FORM_VALIDATION_CONSTANTS = {
  // 触发时机
  TRIGGERS: {
    BLUR: 'blur',
    CHANGE: 'change',
    INPUT: 'input',
    SUBMIT: 'submit',
  },
  
  // 验证规则
  RULES: {
    REQUIRED: 'required',
    EMAIL: 'email',
    MIN_LENGTH: 'minLength',
    MAX_LENGTH: 'maxLength',
    PATTERN: 'pattern',
  },
  
  // 验证消息类型
  MESSAGES: {
    ERROR: 'error',
    WARNING: 'warning',
    SUCCESS: 'success',
    INFO: 'info',
  }
}