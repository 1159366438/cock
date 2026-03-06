package com.example.dto;

/**
 * 打卡请求参数类
 */
public class PunchRequest {
    private String username; // 用户名
    private Integer userId; // 用户ID
    private String punchTime; // 打卡时间（ISO格式）

    // getter和setter方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(String punchTime) {
        this.punchTime = punchTime;
    }

    @Override
    public String toString() {
        return "PunchRequest{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                ", punchTime='" + punchTime + '\'' +
                '}';
    }
}