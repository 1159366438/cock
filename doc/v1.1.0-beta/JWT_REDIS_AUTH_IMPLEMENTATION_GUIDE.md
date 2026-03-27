# 考勤系统JWT + Redis认证实现规范

## 概述

本规范定义了考勤系统中JWT + Redis认证机制的实现方式，以确保系统具有安全、可扩展的身份验证能力。

## 架构设计

### 1. JWT + Redis 方案优势
- **可控性更强**：可以随时撤销Token（从Redis中删除）
- **安全性更高**：双重验证（JWT有效性 + Redis中存在性）
- **灵活性更好**：支持Token刷新机制和额外会话信息存储

### 2. 技术栈
- **后端**：Spring Boot + Spring Security + JWT + Redis
- **前端**：Vue3 + Axios + localStorage

## 实现细节

### 1. 后端实现

#### 1.1 依赖配置
```xml
<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>

<!-- Redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

#### 1.2 配置文件
```yaml
spring:
  data:
    redis:
      host: localhost
      port: 6379
      timeout: 2000ms
      lettuce:
        pool:
          max-active: 8
          max-idle: 8
          min-idle: 0

jwt:
  secret: ${JWT_SECRET:attendance_system_secure_secret_key_for_production_environment_change_this_immediately}
```

#### 1.3 JWT + Redis 工具类 (JwtRedisUtil.java)
- 生成JWT Token并存储到Redis
- 验证Token（JWT验证 + Redis存在性验证）
- 从Token获取用户信息
- 主动撤销Token
- 为特定用户撤销所有Token

#### 1.4 认证过滤器 (JwtAuthenticationFilter.java)
- 从请求头提取JWT Token
- 验证Token有效性
- 设置用户认证信息

#### 1.5 安全配置 (SecurityConfig.java)
- 配置无状态会话
- 允许公开端点访问
- 添加JWT过滤器

#### 1.6 认证控制器 (AuthController.java)
- 登录接口：验证凭据并生成JWT Token
- 登出接口：撤销JWT Token
- 注册接口：用户注册

### 2. 前端实现

#### 2.1 Axios配置
- 请求拦截器：自动添加JWT Token到请求头
- 响应拦截器：处理401未授权错误（Token过期）

#### 2.2 认证API (authApi.ts)
- 登录：发送凭据并接收JWT Token
- 登出：通知后端撤销Token
- 注册：用户注册

#### 2.3 用户状态管理 (user.ts)
- 登录：保存JWT Token到localStorage
- 登出：清除JWT Token
- 自动在请求中使用Token

## 安全考虑

### 1. 密钥管理
- 使用强密钥，不要硬编码在代码中
- 从环境变量或配置中心获取

### 2. Token管理
- 设置合理的过期时间
- 实现Token刷新机制
- 生产环境必须使用HTTPS

### 3. Redis安全
- 配置Redis访问认证
- 设置合适的内存限制
- 定期备份Redis数据

## 错误处理

### 1. 后端错误码
- TOKEN_INVALID_CODE (401): 无效的访问令牌
- TOKEN_EXPIRED_CODE (401): 访问令牌已过期
- TOKEN_MISSING_CODE (401): 缺少访问令牌
- TOKEN_VERIFICATION_FAILED_CODE (401): 令牌验证失败
- AUTHENTICATION_FAILED_CODE (401): 认证失败

### 2. 前端处理
- Token过期时自动跳转到登录页
- 统一错误提示
- 优雅降级处理

## 测试要点

1. 登录/登出功能
2. Token有效性验证
3. Token撤销功能
4. 并发请求处理
5. Token过期处理
6. 异常情况处理

## 部署注意事项

1. 确保Redis服务可用
2. 配置正确的JWT密钥
3. 设置合适的Token过期时间
4. 监控Redis内存使用
5. 实现Redis高可用方案（生产环境）

## 维护指南

1. 定期轮换JWT密钥
2. 监控Token使用情况
3. 检查Redis性能指标
4. 审计认证日志
5. 更新依赖库版本