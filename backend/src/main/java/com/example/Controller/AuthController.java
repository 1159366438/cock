package com.example.controller;

import com.example.common.ResponseResult;
import com.example.constants.AppConstants;
import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.RegisterRequest;
import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.JwtRedisUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 * 提供用户认证相关接口，包括登录、登出等
 * 
 * @author Attendance System Team
 * @since 2026-03-28
 * @version v1.1.0-alpha.1
 */
@Tag(name = "认证管理", description = "用户认证相关接口")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtRedisUtil jwtRedisUtil;

    /**
     * 用户登录接口
     * <p>
     * 该接口用于用户登录认证，验证用户名和密码后返回JWT Token
     * </p>
     *
     * @param loginRequest 登录请求对象，包含用户名和密码
     * @return 标准响应格式，包含JWT Token和用户信息
     * @author Attendance System Team
     * @since 2026-03-28
     * @version v1.1.0-alpha.1
     */
    @Operation(summary = "用户登录", description = "用户登录认证，返回JWT Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "400", description = "用户名或密码不能为空"),
            @ApiResponse(responseCode = "401", description = "用户名或密码错误"),
            @ApiResponse(responseCode = "500", description = "登录失败")
    })
    @PostMapping("/login")
    public ResponseResult<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 验证请求参数
            if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                return ResponseResult.error(AppConstants.Error.BAD_REQUEST_CODE, "用户名或密码不能为空");
            }

            // 验证用户凭据
            User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

            if (user != null) {
                // 生成JWT Token并存储到Redis
                String token = jwtRedisUtil.generateToken(user.getUsername(), user.getId());

                // 构建响应对象
                LoginResponse response = new LoginResponse();
                response.setToken(token);
                response.setUser(user);

                return ResponseResult.success(response);
            } else {
                return ResponseResult.error(AppConstants.Error.LOGIN_FAILED_CODE, "用户名或密码错误");
            }
        } catch (Exception e) {
            logger.error("用户登录异常", e);
            return ResponseResult.error(AppConstants.Error.SERVER_ERROR_CODE, AppConstants.Error.SERVER_ERROR_MSG);
        }
    }

    /**
     * 用户登出接口
     * <p>
     * 该接口用于用户登出，将JWT Token从Redis中移除
     * </p>
     *
     * @param authorizationHeader 授权头，包含JWT Token
     * @return 标准响应格式
     * @author Attendance System Team
     * @since 2026-03-28
     * @version v1.1.0-alpha.1
     */
    @Operation(summary = "用户登出", description = "用户登出，使JWT Token失效")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "登出成功"),
            @ApiResponse(responseCode = "401", description = "未授权访问"),
            @ApiResponse(responseCode = "500", description = "登出失败")
    })
    @PostMapping("/logout")
    public ResponseResult<String> logout(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        logger.info("用户登出请求");

        try {
            String token = null;
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
            }

            if (token != null) {
                // 从Token中获取用户ID并撤销Token
                Integer userId = jwtRedisUtil.getUserIdFromToken(token);
                if (userId != null) {
                    jwtRedisUtil.revokeToken(token);
                    logger.info("用户登出成功: userId={}", userId);
                }
            }

            return ResponseResult.success("登出成功");
        } catch (Exception e) {
            logger.error("用户登出异常", e);
            return ResponseResult.error(AppConstants.Error.SERVER_ERROR_CODE, AppConstants.Error.SERVER_ERROR_MSG);
        }
    }

    /**
     * 用户注册接口
     * <p>
     * 该接口用于新用户注册
     * </p>
     *
     * @param registerRequest 用户注册信息
     * @return 标准响应格式
     * @author Attendance System Team
     * @since 2026-03-28
     * @version v1.1.0-alpha.1
     */
    @Operation(summary = "用户注册", description = "新用户注册")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "注册成功"),
            @ApiResponse(responseCode = "400", description = "用户名已存在或参数错误"),
            @ApiResponse(responseCode = "500", description = "注册失败")
    })
    @PostMapping("/register")
    public ResponseResult<User> register(@RequestBody RegisterRequest registerRequest) {
        logger.info("用户注册请求: username={}", registerRequest.getUsername());

        try {
            // 调用注册业务逻辑
            ResponseResult<UserDTO> dtoResult = userService.register(registerRequest);
            if (dtoResult.getCode() == 200 && dtoResult.getData() != null) {
                // 将UserDTO转换为User实体
                User user = convertUserDtoToEntity(dtoResult.getData());
                logger.info("用户注册成功: userId={}", user.getId());
                return ResponseResult.success(user);
            } else {
                logger.warn("用户注册失败: {}", dtoResult.getMsg());
                return ResponseResult.error(dtoResult.getCode(), dtoResult.getMsg());
            }
        } catch (Exception e) {
            logger.error("用户注册异常", e);
            return ResponseResult.error(AppConstants.Error.SERVER_ERROR_CODE, AppConstants.Error.SERVER_ERROR_MSG);
        }
    }

    /**
     * 将UserDTO转换为User实体
     * 
     * @param userDTO 用户DTO对象
     * @return User实体对象
     */
    private User convertUserDtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());
        user.setAvatar(userDTO.getAvatar());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setCreateTime(userDTO.getCreateTime());
        user.setUpdateTime(userDTO.getUpdateTime());
        user.setIsDeleted(userDTO.getIsDeleted());
        return user;
    }
}