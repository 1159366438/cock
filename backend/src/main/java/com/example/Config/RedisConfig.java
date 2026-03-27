package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类
 * 配置RedisTemplate序列化方式
 * 
 * @author Attendance System Team
 * @since 2026-03-28
 * @version v1.1.0-alpha.1
 */
@Configuration
public class RedisConfig {

    /**
     * 配置RedisTemplate
     *
     * @param factory Redis连接工厂
     * @return RedisTemplate对象
     * @author Attendance System Team
     * @since 2026-03-28
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 使用JdkSerializationRedisSerializer来序列化和反序列化redis的value值
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用JdkSerializationRedisSerializer
        template.setValueSerializer(jdkSerializationRedisSerializer);
        // hash的value序列化方式采用JdkSerializationRedisSerializer
        template.setHashValueSerializer(jdkSerializationRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }
}