# Attendance System 数据库设计文档

## 1. 概述

本文档描述了考勤系统（Attendance System）的数据库设计方案，包括数据库表结构、字段定义、关系设计等。

## 2. 数据库基本信息

- **数据库类型**: MySQL
- **字符集**: utf8mb4
- **排序规则**: utf8mb4_unicode_ci
- **数据库引擎**: InnoDB
- **默认数据库名**: mydatebase（注意：实际项目中可能需要根据配置修改）

## 3. 表结构设计

### 3.1 用户表 (user)

存储系统用户的基本信息。

| 字段名 | 数据类型 | 约束 | 默认值 | 注释 | 说明 |
|--------|----------|------|--------|------|------|
| id | INT | PRIMARY KEY, AUTO_INCREMENT | - | - | 主键，自增ID |
| username | VARCHAR(50) | NOT NULL, UNIQUE | - | - | 用户名，唯一约束 |
| password | VARCHAR(255) | - | $2a$10$NQVgZc5sQB7FvHMRxJrwkedBqMTMtwL0C2YdytKE.Ur9eyo9ydwYm | - | 密码，BCrypt加密，初始默认值为'123456'的哈希值 |
| age | INT | - | - | - | 年龄 |
| avatar | VARCHAR(255) | - | - | - | 头像URL |
| create_time | TIMESTAMP | - | CURRENT_TIMESTAMP | - | 创建时间 |

**索引**:
- 主键索引: id
- 唯一索引: username

### 3.2 打卡记录表 (punch_record)

存储用户的打卡记录。

| 字段名 | 数据类型 | 约束 | 默认值 | 注释 | 说明 |
|--------|----------|------|--------|------|------|
| id | INT | PRIMARY KEY, AUTO_INCREMENT | - | - | 主键，自增ID |
| user_id | INT | NOT NULL | - | - | 外键，关联用户表 |
| check_in_time | TIMESTAMP | NOT NULL | - | - | 打卡时间 |
| check_in_type | INT | NOT NULL | - | 1-上班打卡 2-下班打卡 3-加班打卡 | 打卡类型 |
| check_in_status | INT | NOT NULL | - | 1-正常 2-迟到 3-早退 4-旷工 | 打卡状态 |
| check_in_location | VARCHAR(100) | - | - | - | 打卡地点 |
| create_time | TIMESTAMP | - | CURRENT_TIMESTAMP | - | 记录创建时间 |
| update_time | TIMESTAMP | - | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | - | 记录更新时间 |

**索引**:
- 主键索引: id
- 外键索引: user_id (外键约束，级联删除和更新)

## 4. 表关系

### 4.1 外键关系

- `punch_record.user_id` → `user.id`
  - 级联删除: ON DELETE CASCADE
  - 级联更新: ON UPDATE CASCADE

### 4.2 关系图

```
[User] 1 ←→ * [PunchRecord]
```

## 5. SQL DDL

```sql
-- 创建数据库
CREATE DATABASE IF NOT EXISTS `mydatebase` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `mydatebase`;

-- 创建用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `password` VARCHAR(255) DEFAULT '$2a$10$NQVgZc5sQB7FvHMRxJrwkedBqMTMtwL0C2YdytKE.Ur9eyo9ydwYm', -- 默认密码为 '123456' 的BCrypt哈希值
  `age` INT,
  `avatar` VARCHAR(255),
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建打卡记录表
CREATE TABLE IF NOT EXISTS `punch_record` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `check_in_time` TIMESTAMP NOT NULL,
  `check_in_type` INT NOT NULL COMMENT '1-上班打卡 2-下班打卡 3-加班打卡',
  `check_in_status` INT NOT NULL COMMENT '1-正常 2-迟到 3-早退 4-旷工',
  `check_in_location` VARCHAR(100),
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

## 6. 测试数据

```sql
-- 添加测试数据
INSERT INTO `user` (`username`, `age`) VALUES ('admin', 25);
INSERT INTO `user` (`username`, `age`) VALUES ('user1', 30);
INSERT INTO `user` (`username`, `age`) VALUES ('user2', 28);
```

## 7. 注意事项

1. **数据库命名**: 当前SQL脚本中数据库名为`mydatebase`，在实际部署时可能需要根据环境配置进行修改。
2. **安全性**: 密码使用BCrypt算法加密存储。
3. **时间戳**: 所有时间相关字段使用TIMESTAMP类型，支持自动更新。
4. **字符集**: 所有表均使用utf8mb4字符集，支持完整的Unicode字符。
5. **外键约束**: 打卡记录表与用户表之间建立了外键约束，确保数据完整性。
6. **性能优化**: 可考虑在经常查询的字段上建立索引，如`user_id`、`check_in_time`等。

## 8. 版本信息

- **文档版本**: v1.0.1-beta
- **最后更新**: 2026-03-12
- **作者**: Attendance System 开发团队