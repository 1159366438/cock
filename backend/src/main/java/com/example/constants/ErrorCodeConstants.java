package com.example.constants;

/**
 * 错误码常量类
 * @author Attendance System Team
 * @since 2026-03-15
 */
public class ErrorCodeConstants {
    
    /**
     * 通用错误码
     */
    public static class Common {
        /** 成功 */
        public static final int SUCCESS_CODE = 200;
        public static final String SUCCESS_MSG = "操作成功";
        
        /** 服务器内部错误 */
        public static final int SERVER_ERROR_CODE = 500;
        public static final String SERVER_ERROR_MSG = "服务器内部错误";
        
        /** 请求参数错误 */
        public static final int BAD_REQUEST_CODE = 400;
        public static final String BAD_REQUEST_MSG = "请求参数错误";
        
        /** 未找到 */
        public static final int NOT_FOUND_CODE = 404;
        public static final String NOT_FOUND_MSG = "未找到";
        
        /** 未授权 */
        public static final int UNAUTHORIZED_CODE = 401;
        public static final String UNAUTHORIZED_MSG = "未授权访问";
    }
    
    /**
     * 用户相关错误码
     */
    public static class User {
        /** 用户名不能为空 */
        public static final int USERNAME_EMPTY_CODE = 400;
        public static final String USERNAME_EMPTY_MSG = "用户名不能为空";
        
        /** 密码不能为空 */
        public static final int PASSWORD_EMPTY_CODE = 400;
        public static final String PASSWORD_EMPTY_MSG = "密码不能为空";
        
        /** 密码不匹配 */
        public static final int PASSWORD_MISMATCH_CODE = 400;
        public static final String PASSWORD_MISMATCH_MSG = "两次输入的密码不一致";
        
        /** 用户名或密码错误 */
        public static final int LOGIN_FAILED_CODE = 401;
        public static final String LOGIN_FAILED_MSG = "用户名或密码错误";
        
        /** 用户不存在 */
        public static final int USER_NOT_EXIST_CODE = 404;
        public static final String USER_NOT_EXIST_MSG = "用户不存在";
        
        /** 用户已存在 */
        public static final int USER_EXISTS_CODE = 409;
        public static final String USER_EXISTS_MSG = "用户名已存在";
        
        /** 获取用户信息失败 */
        public static final int GET_USER_INFO_FAILED_CODE = 500;
        public static final String GET_USER_INFO_FAILED_MSG = "获取用户信息失败";
        
        /** 注册失败 */
        public static final int REGISTER_FAILED_CODE = 500;
        public static final String REGISTER_FAILED_MSG = "注册失败";
    }
    
    /**
     * 打卡相关错误码
     */
    public static class Punch {
        /** 用户ID不能为空 */
        public static final int USER_ID_EMPTY_CODE = 400;
        public static final String USER_ID_EMPTY_MSG = "用户ID不能为空";
        
        /** 打卡失败 */
        public static final int PUNCH_FAILED_CODE = 500;
        public static final String PUNCH_FAILED_MSG = "打卡失败";
        
        /** 获取打卡记录失败 */
        public static final int GET_PUNCH_RECORDS_FAILED_CODE = 500;
        public static final String GET_PUNCH_RECORDS_FAILED_MSG = "获取打卡记录失败";
    }
    
    /**
     * 参数校验相关错误码
     */
    public static class Validation {
        /** 参数校验失败 */
        public static final int VALIDATION_FAILED_CODE = 400;
        public static final String VALIDATION_FAILED_MSG = "参数校验失败";
        
        /** 通用验证错误 */
        public static final int VALIDATION_ERROR_CODE = 400;
        public static final String VALIDATION_ERROR_MSG = "参数验证错误";
    }
}