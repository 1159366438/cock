package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 标识为REST接口，返回JSON数据
@RequestMapping("/api/user") // 匹配前端url的前缀 /api/input
@CrossOrigin // 解决前后端分离的跨域问题（必加，否则前端请求会被拦截）
@Validated // 开启参数校验
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 获取打卡记录接口
     * <p>
     * 该接口用于获取用户的打卡记录信息
     * </p>
     *
     * @return User对象，表示查询到的用户信息
     * @since 1.0.0
     */
    @GetMapping("/info")
    public User getUserInfo() {

        System.out.println("请求成功");
        // 通过服务层查询用户信息
        return userService.queryById(1);
    }

}