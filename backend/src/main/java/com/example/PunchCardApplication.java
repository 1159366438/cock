package com.example;

/**
 * 考勤系统启动类
 * @author Attendance System Team
 * @since 2026-03-15
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.dao"})

public class PunchCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(PunchCardApplication.class, args);
    }

}