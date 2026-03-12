package com.example.controller;

import com.example.common.ResponseResult;
import com.example.dto.PunchRequest;
import com.example.entity.User;
import com.example.service.PunchRecordService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // 标识为REST接口，返回JSON数据
@RequestMapping("/api/punch") // 匹配前端url的前缀 /api/input
@CrossOrigin // 解决前后端分离的跨域问题（必加，否则前端请求会被拦截）
@Validated // 开启参数校验
public class PunchController {

    @Autowired
    private PunchRecordService punchRecordService;

    @Autowired
    private UserService userService;

    /**
     * 获取打卡记录接口（分页）
     * <p>
     * 该接口用于获取用户的打卡记录信息（支持分页）
     * </p>
     * 
     * @param page 页码，默认为1
     * @param size 每页数量，默认为15
     * @return 包含打卡记录列表和总数的响应
     * @since 1.0.0
     */
    @GetMapping("/record")
    public ResponseResult<Map<String, Object>> getPunchRecords(
            @RequestParam("userId") Integer userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size) {

        System.out.println("获取打卡记录请求成功，用户ID: " + userId + ", 页码: " + page + ", 每页数量: " + size);
        
        // 委托给服务层处理业务逻辑
        return punchRecordService.getPunchRecords(userId, page, size);
    }

    /**
     * 打卡接口
     * <p>
     * 该接口用于处理用户的打卡请求，保存打卡记录
     * </p>
     *
     * @param punchRequest 打卡请求参数，包含用户名和打卡时间
     * @return 标准响应格式
     * @since 1.0.0
     */
    @PostMapping("/in")
    public ResponseResult<String> punchIn(@RequestBody PunchRequest punchRequest) {
        System.out.println("打卡请求：" + punchRequest);

        try {
            // 1. 获取前台传递的用户ID
            Integer userId = punchRequest.getUserId();
            if (userId == null) {
                return ResponseResult.error(400, "用户ID不能为空");
            }

            // 2. 查询用户信息，确保用户存在
            User user = userService.queryById(userId);
            if (user == null) {
                return ResponseResult.error(404, "用户不存在");
            }

            // 3. 委托给服务层处理打卡业务
            return punchRecordService.performPunchIn(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error(500, "打卡失败");
        }
    }

}