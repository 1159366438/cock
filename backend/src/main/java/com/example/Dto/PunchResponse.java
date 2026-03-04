package com.example.dto;

/**
 * 打卡响应类
 */
public class PunchResponse {
    private int code; // 状态码
    private String message; // 消息

    public PunchResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // getter方法
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}