package com.example.config;

/**
 * 全局异常处理器
 * 统一处理系统中的异常并返回标准化错误响应
 * 
 * @author Attendance System Team
 * @since 2026-03-28
 * @version v1.1.0-alpha.1
 */

import com.example.common.ResponseResult;
import com.example.constants.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * 
 * @author Attendance System Team
 * @since 2026-03-28
 * @version v1.1.0-alpha.1
 * @description 全局异常处理器，用于捕获并处理系统中的异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * 处理所有未被捕获的异常
     * 重要：不暴露任何内部信息给客户端
     *
     * @param e 异常对象
     * @return 统一响应结果
     * @author Attendance System Team
     * @since 2026-03-28
     * @version v1.1.0-alpha.1
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handleGenericException(Exception e) {
        // 记录详细的错误信息，但不返回给客户端
        logger.error("系统发生未预期异常", e);
        return ResponseResult.error(AppConstants.Error.SERVER_ERROR_CODE, "系统繁忙，请稍后再试");
    }
    
    /**
     * 处理参数校验异常
     *
     * @param ex MethodArgumentNotValidException异常对象
     * @return 统一响应结果，包含校验错误详情
     * @author Attendance System Team
     * @since 2026-03-28
     * @version v1.1.0-alpha.1
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        logger.warn("参数校验失败: {}", errors);
        return ResponseResult.error(AppConstants.Error.VALIDATION_FAILED_CODE, AppConstants.Error.VALIDATION_FAILED_MSG, errors);
    }
    
    /**
     * 处理参数类型不匹配异常
     *
     * @param e MethodArgumentTypeMismatchException异常对象
     * @return 统一响应结果
     * @author Attendance System Team
     * @since 2026-03-28
     * @version v1.1.0-alpha.1
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseResult<String> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.warn("参数类型不匹配: 参数 '{}' 期望类型为 '{}', 实际值为 '{}'", 
                   e.getName(), e.getRequiredType(), e.getValue());
        return ResponseResult.error(AppConstants.Error.BAD_REQUEST_CODE, "请求参数类型错误");
    }
    
    /**
     * 处理缺少请求参数异常
     *
     * @param e MissingServletRequestParameterException异常对象
     * @return 统一响应结果
     * @author Attendance System Team
     * @since 2026-03-28
     * @version v1.1.0-alpha.1
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult<String> handleMissingParamException(MissingServletRequestParameterException e) {
        logger.warn("缺少请求参数: 参数 '{}' 为必填项", e.getParameterName());
        return ResponseResult.error(AppConstants.Error.BAD_REQUEST_CODE, "缺少必需的请求参数: " + e.getParameterName());
    }
    
    /**
     * 处理HTTP请求方法不支持异常
     *
     * @param e HttpRequestMethodNotSupportedException异常对象
     * @return 统一响应结果
     * @author Attendance System Team
     * @since 2026-03-28
     * @version v1.1.0-alpha.1
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult<String> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.warn("HTTP方法不支持: {} 请求方法不被支持", e.getMethod());
        return ResponseResult.error(HttpStatus.METHOD_NOT_ALLOWED.value(), "请求方法不被支持");
    }
    
    /**
     * 处理404异常
     *
     * @param e NoHandlerFoundException异常对象
     * @return 统一响应结果
     * @author Attendance System Team
     * @since 2026-03-28
     * @version v1.1.0-alpha.1
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResult<String> handleNotFoundException(NoHandlerFoundException e) {
        logger.warn("请求路径不存在: {}", e.getRequestURL());
        return ResponseResult.error(HttpStatus.NOT_FOUND.value(), "请求的资源不存在");
    }
    
    /**
     * 处理数据库访问异常
     *
     * @param e DataAccessException异常对象
     * @return 统一响应结果
     * @author Attendance System Team
     * @since 2026-03-28
     * @version v1.1.0-alpha.1
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseResult<String> handleDataAccessException(DataAccessException e) {
        logger.error("数据库访问异常", e);
        return ResponseResult.error(AppConstants.Error.SERVER_ERROR_CODE, "数据操作失败，请稍后重试");
    }
}