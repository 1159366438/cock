package com.example.Controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController // 标识为REST接口，返回JSON数据
@RequestMapping("/api/input") // 匹配前端url的前缀 /api/input
@CrossOrigin // 解决前后端分离的跨域问题（必加，否则前端请求会被拦截）
@Validated // 开启参数校验
public class TestController
{
    @GetMapping("/send")
    public  String TEST (@RequestParam(value = "content") String content){
        System.out.println("请求成功");
        System.out.println(content);
        return "TEST";
    }
}
