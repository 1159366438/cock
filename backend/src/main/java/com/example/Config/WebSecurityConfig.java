package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Web安全配置类
 * 配置Spring Security以支持JWT认证，禁用默认的CSRF保护和会话管理
 * 
 * @author Attendance System Team
 * @since 2026-03-28
 * @version v1.1.0-alpha.1
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 配置安全过滤器链
     * 
     * @param http HttpSecurity对象
     * @return SecurityFilterChain实例
     * @throws Exception 配置异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 禁用基本认证
            .httpBasic(basic -> basic.disable())
            // 禁用表单登录
            .formLogin(form -> form.disable())
            // 禁用登出功能（使用自定义登出接口）
            .logout(logout -> logout.disable())
            // 禁用CSRF保护（API模式下不需要）
            .csrf(csrf -> csrf.disable())
            // 禁用默认session管理，使用无状态认证
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 配置CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // 配置请求授权规则
            .authorizeHttpRequests(authz -> authz
                // 允许访问API文档相关端点
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                // 允许认证相关接口
                .requestMatchers("/api/auth/**").permitAll()
                // 允许静态资源
                .requestMatchers("/static/**", "/public/**", "/assets/**").permitAll()
                // 允许健康检查端点
                .requestMatchers("/actuator/health", "/actuator/info").permitAll()
                // 其他所有请求都需要认证
                .anyRequest().authenticated()
            );
            // 添加JWT认证过滤器
            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 配置CORS策略
     * 
     * @return CorsConfigurationSource实例
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*")); // 生产环境中应限制为具体的域名
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}