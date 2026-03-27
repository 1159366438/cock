package com.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * JWT + Redis 工具类
 * 用于生成和验证JWT Token，并使用Redis存储Token以支持主动撤销
 * 
 * @author Attendance System Team
 * @since 2026-03-28
 * @version v1.1.0-alpha.1
 */
@Component
public class JwtRedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtRedisUtil.class);

    // Token前缀
    private static final String TOKEN_PREFIX = "attendance:token:";
    // 用户Token映射前缀
    private static final String USER_TOKEN_PREFIX = "attendance:user:token:";
    // Token过期时间（秒）- 24小时
    private static final long EXPIRATION_TIME = 86400;

    @Value("${jwt.secret:ZXhhbXBsZV9zZWNyZXRfZm9yX2F0dGVuZGFuY2Vfc3lzdGVtX2NoYW5nZV90aGlzX2ltbWVkaWF0ZWx5}")
    private String secret;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 生成JWT Token并存储到Redis
     *
     * @param username 用户名
     * @param userId 用户ID
     * @return JWT Token字符串
     * @author Attendance System Team
     * @since 2026-03-28
     */
    public String generateToken(String username, Integer userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME * 1000);

        // 1. 将密钥字符串转为 SecretKey（使用 UTF-8 编码）
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String token = Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)                     // 自动使用密钥对应的算法（HS256/384/512）
                // 或显式指定算法（需与密钥长度匹配）
                // .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        
        // 存储到Redis，设置过期时间
        String tokenKey = TOKEN_PREFIX + token;
        String userTokenKey = USER_TOKEN_PREFIX + userId;

        // 存储token -> userId信息
        redisTemplate.opsForValue().set(tokenKey, userId, EXPIRATION_TIME, TimeUnit.SECONDS);
        // 存储userId -> token信息（用于主动撤销）
        redisTemplate.opsForValue().set(userTokenKey, token, EXPIRATION_TIME, TimeUnit.SECONDS);
        
        return token;
    }

    /**
     * 验证Token（JWT验证 + Redis存在性验证）
     *
     * @param token JWT Token字符串
     * @return 验证结果
     * @author Attendance System Team
     * @since 2026-03-28
     */
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // 验证Token是否已被撤销（存在于Redis中）
            String tokenKey = TOKEN_PREFIX + token;
            Object storedUserId = redisTemplate.opsForValue().get(tokenKey);
            
            if (storedUserId == null) {
                return false; // 不抛异常，直接返回false
            }

            boolean isExpired = claims.getExpiration().before(new Date());
            return storedUserId != null && !isExpired;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从Token获取用户名
     *
     * @param token JWT Token字符串
     * @return 用户名
     * @author Attendance System Team
     * @since 2026-03-28
     */
    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从Token获取用户ID
     *
     * @param token JWT Token字符串
     * @return 用户ID
     * @author Attendance System Team
     * @since 2026-03-28
     */
    public Integer getUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("userId", Integer.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 主动撤销Token
     *
     * @param token JWT Token字符串
     * @author Attendance System Team
     * @since 2026-03-28
     */
    public void revokeToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Integer userId = claims.get("userId", Integer.class);

            // 删除Token相关记录
            String tokenKey = TOKEN_PREFIX + token;
            String userTokenKey = USER_TOKEN_PREFIX + userId;

            redisTemplate.delete(tokenKey);
            redisTemplate.delete(userTokenKey);
        } catch (Exception e) {
            // Token无效，无需处理
        }
    }

    /**
     * 为特定用户撤销所有Token
     *
     * @param userId 用户ID
     * @author Attendance System Team
     * @since 2026-03-28
     */
    public void revokeUserTokens(Integer userId) {
        String userTokenKey = USER_TOKEN_PREFIX + userId;
        String tokenStr = (String) redisTemplate.opsForValue().get(userTokenKey);
        if (tokenStr != null) {
            String tokenKey = TOKEN_PREFIX + tokenStr;
            redisTemplate.delete(tokenKey);
            redisTemplate.delete(userTokenKey);
        }
    }
}