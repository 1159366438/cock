# 考勤系统故障排除指南 (v1.0.1-beta)

## 1. 常见问题及解决方案

### 1.1 登录认证问题

#### 问题描述
- 用户登录时出现认证失败
- BCrypt密码哈希验证不通过

#### 解决方案
- 确认数据库中存储的密码哈希值是正确的BCrypt格式
- 旧的错误哈希值：`$2a$10$NQVgZc5sQB7FvHMRxJrwkedBqMTMtwL0C2YdytKE.Ur9eyo9ydwYm`
- 当前正确的哈希值：`$2a$10$byZHeNtemB1DFSAPJgGLzuuVLHig9xsUq4fbVaip806VYU1mFO9BG` (对应明文密码 '123456')

#### 数据库修复SQL
```sql
-- 修复前的错误数据
-- 添加测试数据
INSERT INTO `user` (`username`, `password`, `age`) VALUES ('admin', '$2a$10$byZHeNtemB1DFSAPJgGLzuuVLHig9xsUq4fbVaip806VYU1mFO9BG', 25); -- 密码为 '123456' 的BCrypt哈希值
INSERT INTO `user` (`username`, `password`, `age`) VALUES ('user1', '$2a$10$byZHeNtemB1DFSAPJgGLzuuVLHig9xsUq4fbVaip806VYU1mFO9BG', 30); -- 密码为 '123456' 的BCrypt哈希值
INSERT INTO `user` (`username`, `password`, `age`) VALUES ('user2', '$2a$10$byZHeNtemB1DFSAPJgGLzuuVLHig9xsUq4fbVaip806VYU1mFO9BG', 28); -- 密码为 '123456' 的BCrypt哈希值
```

### 1.2 前端组件废弃API警告

#### 问题描述
- Element Plus组件中使用了已废弃的API
- 控制台显示：`[props] [API] type.text is about to be deprecated in version 3.0.0, please use link instead.`

#### 解决方案
- 在UserInfo.vue文件中，将`<el-button type="text">`改为`<el-button link>`
- 修改后的代码：
```html
<el-button link class="logout-btn" @click="emit(EVENT_CONSTANTS.USER.LOGOUT)">
  {{ logoutText }}
</el-button>
```

### 1.3 Git分支管理问题

#### 问题描述
- 网络连接不稳定导致Git操作失败
- 分支切换时存在未提交的更改
- 合并冲突处理

#### 解决方案
- 配置代理服务器以解决网络问题
- 提交当前更改后再切换分支
- 正确处理合并冲突并完成合并过程

## 2. 数据库问题

### 2.1 密码哈希值修复

之前使用的BCrypt哈希值有误，已更新为正确的哈希值：
- 旧值（错误）：`$2a$10$NQVgZc5sQB7FvHMRxJrwkedBqMTMtwL0C2YdytKE.Ur9eyo9ydwYm`
- 新值（正确）：`$2a$10$byZHeNtemB1DFSAPJgGLzuuVLHig9xsUq4fbVaip806VYU1mFO9BG`

### 2.2 自动修复机制

系统已实现自动密码哈希修复功能，在`PasswordFixer.java`中实现了启动时自动检测和修复不正确的密码哈希值。

## 3. 版本信息

- **文档版本**: v1.0.1-beta
- **最后更新**: 2026-03-12
- **作者**: Attendance System 开发团队