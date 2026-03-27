package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置类
 * 配置密码编码器等安全相关组件
 * 
 * @author Attendance System Team
 * @since 2026-03-28
 * @version v1.1.0-alpha.1
 */
@Configuration
public class SecurityConfig {

    /**
     * 配置密码编码器
     * 使用BCrypt算法进行密码加密
     * 
     * @return PasswordEncoder实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}