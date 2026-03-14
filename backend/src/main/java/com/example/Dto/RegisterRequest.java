package com.example.dto;

/**
 * 用户注册请求参数类
 *
 * @author Attendance System Team
 * @since 2026-03-14
 */
public class RegisterRequest {
    private String username; // 用户名
    private String password; // 密码
    private String confirmPassword; // 确认密码
    private Integer age; // 年龄
    private String avatar; // 头像
    private Integer gender; // 性别

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", age=" + age +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                '}';
    }
}