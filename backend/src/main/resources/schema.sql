-- 创建数据库
CREATE DATABASE IF NOT EXISTS `mydatebase` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `mydatebase`;

-- 创建用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
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

-- 添加测试数据
INSERT INTO `user` (`username`, `age`) VALUES ('admin', 25);
INSERT INTO `user` (`username`, `age`) VALUES ('user1', 30);
INSERT INTO `user` (`username`, `age`) VALUES ('user2', 28);