---
name: "login-page-generator"
description: "Generates a complete login page with form validation, internationalization, and styling. Invoke when user needs to create a login page component."
---

# 登录页面生成器

此技能用于生成一个完整的登录页面组件，包含表单验证、国际化支持和样式。

## 功能特性

- 创建包含用户名/密码输入框的登录表单
- 实现表单验证功能
- 支持国际化文本
- 集成Element Plus UI组件
- 符合项目架构和代码规范

## 使用场景

当用户需要创建登录页面时调用此技能。

## 组件结构

生成的登录页面将包含以下元素：

1. 用户名输入框（带验证）
2. 密码输入框（带验证）
3. 登录按钮
4. 忘记密码链接
5. 注册账户链接
6. 表单验证逻辑

## 技术实现

- 使用Vue 3 Composition API
- 集成Element Plus组件库
- 使用国际化函数t()处理文本
- 遵循项目代码规范
- 包含适当的错误处理机制

## 文件输出

- `src/views/Login.vue` - 登录页面组件
- 可能更新相关路由配置
- 添加必要的国际化词条