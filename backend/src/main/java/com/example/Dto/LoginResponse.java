package com.example.dto;

import com.example.entity.User;

/**
 * 登录响应DTO
 * 用于封装登录成功后的响应数据
 * 
 * @author Attendance System Team
 * @since 2026-03-28
 * @version v1.1.0-alpha.1
 */
public class LoginResponse {
    private String token;
    private User user;

    // 默认构造函数
    public LoginResponse() {}

    // 带参构造函数
    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    // Getter和Setter方法
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}