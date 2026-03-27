package com.example.config;

import com.example.util.JwtRedisUtil;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * JWT认证过滤器
 * 用于在每次请求时验证JWT Token的有效性
 * 
 * @author Attendance System Team
 * @since 2026-03-28
 * @version v1.1.0-alpha.1
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtRedisUtil jwtRedisUtil;

    /**
     * 过滤请求，验证JWT Token
     *
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param filterChain FilterChain对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     * @author Attendance System Team
     * @since 2026-03-28
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        String token = extractToken(request);
        
        if (token != null && jwtRedisUtil.validateToken(token)) {
            String username = jwtRedisUtil.getUsernameFromToken(token);
            
            // 设置用户认证信息
            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        // 继续执行过滤器链，让Spring Security处理授权
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求头中提取Token
     *
     * @param request HttpServletRequest对象
     * @return Token字符串
     * @author Attendance System Team
     * @since 2026-03-28
     */
    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}