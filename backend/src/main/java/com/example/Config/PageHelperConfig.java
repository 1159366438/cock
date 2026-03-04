package com.example.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * PageHelper分页插件配置类
 */
@Configuration
public class PageHelperConfig {

    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        // 方言，自动识别数据库类型
        properties.setProperty("helperDialect", "mysql");
        // 是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        // 是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        // 是否分页合理化
        properties.setProperty("reasonable", "true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}