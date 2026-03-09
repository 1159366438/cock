import { BUSINESS_STATUS } from '../constants/api';

// 统一处理后端响应。有些接口返回 {code,msg,...}，
// 有些返回原始数据并依赖 HTTP 状态。
// 这个工具函数抽象化这种检查逻辑，返回统一格式。

export interface NormalizedResponse<T = any> {
  success: boolean;
  data: T;
  message?: string;
  status: number;
}

export function normalizeResponse<T>(res: any): NormalizedResponse<T> {
  const status = res?.status || 0;
  const payload = res?.data;

  // 如果响应体有 code 字段，作为业务响应处理
  if (payload && typeof payload === 'object' && 'code' in payload) {
    // 后端可能返回 code 和 message 字段
    const code = payload.code;
    // 同时要求 HTTP 状态 200 和业务码 200 才视为成功
    const success = status === BUSINESS_STATUS.SUCCESS && code === BUSINESS_STATUS.SUCCESS;
    return {
      success,
      data: payload,
      message: payload.message || payload.msg || '',
      status,
    };
  }

  // 降级方案：仅依赖 HTTP 状态码
  const success = status === BUSINESS_STATUS.SUCCESS;
  return {
    success,
    data: payload,
    message: '',
    status,
  };
}
