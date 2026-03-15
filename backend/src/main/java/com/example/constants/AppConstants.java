package com.example.constants;

/**
 * 应用程序常量类
 * @author Attendance System Team
 * @since 2026-03-15
 */
public class AppConstants {
    
    /**
     * 分页相关常量
     */
    public static class Page {
        /** 默认页码 */
        public static final int DEFAULT_PAGE_NUM = 1;
        
        /** 默认每页数量 */
        public static final int DEFAULT_PAGE_SIZE = 15;
    }
    
    /**
     * 打卡相关常量
     */
    public static class Punch {
        // 打卡类型
        /** 上班打卡 */
        public static final int CHECK_IN_TYPE_ON_WORK = 1;
        
        /** 下班打卡 */
        public static final int CHECK_IN_TYPE_OFF_WORK = 2;
        
        /** 加班打卡 */
        public static final int CHECK_IN_TYPE_OVERTIME = 3;
        
        // 打卡状态
        /** 正常 */
        public static final int CHECK_IN_STATUS_NORMAL = 1;
        
        /** 迟到 */
        public static final int CHECK_IN_STATUS_LATE = 2;
        
        /** 早退 */
        public static final int CHECK_IN_STATUS_LEAVE_EARLY = 3;
        
        /** 旷工 */
        public static final int CHECK_IN_STATUS_ABSENTEEISM = 4;
    }
    
    /**
     * 用户相关常量
     */
    public static class User {
        /** 默认用户ID（仅用于演示）*/
        public static final int DEFAULT_USER_ID = 1;
        

    }
    
    /**
     * CORS相关常量
     */
    public static class Cors {
        /** 前端开发服务器地址 */
        public static final String FRONTEND_URL_LOCAL = "http://localhost:5173";
    }
    
    /**
     * 错误码相关常量
     */
    public static class Error {
        public static final int SUCCESS_CODE = ErrorCodeConstants.Common.SUCCESS_CODE;
        public static final String SUCCESS_MSG = ErrorCodeConstants.Common.SUCCESS_MSG;
        
        public static final int SERVER_ERROR_CODE = ErrorCodeConstants.Common.SERVER_ERROR_CODE;
        public static final String SERVER_ERROR_MSG = ErrorCodeConstants.Common.SERVER_ERROR_MSG;
        
        public static final int BAD_REQUEST_CODE = ErrorCodeConstants.Common.BAD_REQUEST_CODE;
        public static final String BAD_REQUEST_MSG = ErrorCodeConstants.Common.BAD_REQUEST_MSG;
        
        public static final int NOT_FOUND_CODE = ErrorCodeConstants.Common.NOT_FOUND_CODE;
        public static final String NOT_FOUND_MSG = ErrorCodeConstants.Common.NOT_FOUND_MSG;
        
        public static final int UNAUTHORIZED_CODE = ErrorCodeConstants.Common.UNAUTHORIZED_CODE;
        public static final String UNAUTHORIZED_MSG = ErrorCodeConstants.Common.UNAUTHORIZED_MSG;
        
        // 用户相关错误码
        public static final int USERNAME_EMPTY_CODE = ErrorCodeConstants.User.USERNAME_EMPTY_CODE;
        public static final String USERNAME_EMPTY_MSG = ErrorCodeConstants.User.USERNAME_EMPTY_MSG;
        
        public static final int PASSWORD_EMPTY_CODE = ErrorCodeConstants.User.PASSWORD_EMPTY_CODE;
        public static final String PASSWORD_EMPTY_MSG = ErrorCodeConstants.User.PASSWORD_EMPTY_MSG;
        
        public static final int PASSWORD_MISMATCH_CODE = ErrorCodeConstants.User.PASSWORD_MISMATCH_CODE;
        public static final String PASSWORD_MISMATCH_MSG = ErrorCodeConstants.User.PASSWORD_MISMATCH_MSG;
        
        public static final int LOGIN_FAILED_CODE = ErrorCodeConstants.User.LOGIN_FAILED_CODE;
        public static final String LOGIN_FAILED_MSG = ErrorCodeConstants.User.LOGIN_FAILED_MSG;
        
        public static final int USER_NOT_EXIST_CODE = ErrorCodeConstants.User.USER_NOT_EXIST_CODE;
        public static final String USER_NOT_EXIST_MSG = ErrorCodeConstants.User.USER_NOT_EXIST_MSG;
        
        public static final int USER_EXISTS_CODE = ErrorCodeConstants.User.USER_EXISTS_CODE;
        public static final String USER_EXISTS_MSG = ErrorCodeConstants.User.USER_EXISTS_MSG;
        
        public static final int GET_USER_INFO_FAILED_CODE = ErrorCodeConstants.User.GET_USER_INFO_FAILED_CODE;
        public static final String GET_USER_INFO_FAILED_MSG = ErrorCodeConstants.User.GET_USER_INFO_FAILED_MSG;
        
        public static final int REGISTER_FAILED_CODE = ErrorCodeConstants.User.REGISTER_FAILED_CODE;
        public static final String REGISTER_FAILED_MSG = ErrorCodeConstants.User.REGISTER_FAILED_MSG;
        
        // 打卡相关错误码
        public static final int USER_ID_EMPTY_CODE = ErrorCodeConstants.Punch.USER_ID_EMPTY_CODE;
        public static final String USER_ID_EMPTY_MSG = ErrorCodeConstants.Punch.USER_ID_EMPTY_MSG;
        
        public static final int PUNCH_FAILED_CODE = ErrorCodeConstants.Punch.PUNCH_FAILED_CODE;
        public static final String PUNCH_FAILED_MSG = ErrorCodeConstants.Punch.PUNCH_FAILED_MSG;
        
        public static final int GET_PUNCH_RECORDS_FAILED_CODE = ErrorCodeConstants.Punch.GET_PUNCH_RECORDS_FAILED_CODE;
        public static final String GET_PUNCH_RECORDS_FAILED_MSG = ErrorCodeConstants.Punch.GET_PUNCH_RECORDS_FAILED_MSG;
        
        // 参数校验相关错误码
        public static final int VALIDATION_FAILED_CODE = ErrorCodeConstants.Validation.VALIDATION_FAILED_CODE;
        public static final String VALIDATION_FAILED_MSG = ErrorCodeConstants.Validation.VALIDATION_FAILED_MSG;
        
        public static final int VALIDATION_ERROR_CODE = ErrorCodeConstants.Validation.VALIDATION_ERROR_CODE;
        public static final String VALIDATION_ERROR_MSG = ErrorCodeConstants.Validation.VALIDATION_ERROR_MSG;
    }
    
    /**
     * 成功消息相关常量
     */
    public static class Success {
        public static final String PUNCH_SUCCESS_MSG = "打卡成功";
        public static final String LOGOUT_SUCCESS_MSG = "登出成功";
        public static final String OPERATION_SUCCESS_MSG = "操作成功";
    }
}