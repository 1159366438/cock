package com.example.dto;

/**
 * 登录请求参数类
 * @author Attendance System Team
 * @since 2026-03-15
 */
public class LoginRequest {
    private String username; // 用户名
    private String password; // 密码

    // getter和setter方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}