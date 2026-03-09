import { BUSINESS_STATUS } from '../constants/api';

// Normalize backend responses. Some endpoints return {code,msg,...},
// others return raw data and rely on HTTP status.
// This helper abstracts the check and returns a uniform object.

export interface NormalizedResponse<T = any> {
  success: boolean;
  data: T;
  message?: string;
  status: number;
}

export function normalizeResponse<T>(res: any): NormalizedResponse<T> {
  const status = res?.status || 0;
  const payload = res?.data;

  // If response body has a "code" field, assume it's the business code.
  if (payload && typeof payload === 'object' && 'code' in payload) {
    const success = payload.code === BUSINESS_STATUS.SUCCESS;
    return {
      success,
      data: payload,
      message: payload.msg || '',
      status
    };
  }

  // Otherwise fall back to HTTP status success check (200)
  const success = status === BUSINESS_STATUS.SUCCESS;
  return {
    success,
    data: payload,
    status
  };
}
