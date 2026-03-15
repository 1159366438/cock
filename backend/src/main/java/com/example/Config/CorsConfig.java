package com.example.config;

/**
 * CORS配置类
 * @author Attendance System Team
 * @since 2026-03-15
 */

import com.example.constants.AppConstants;
import jakarta.servlet.*;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//跨域配置类型
@Configuration
    public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(AppConstants.Cors.FRONTEND_URL_LOCAL)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}