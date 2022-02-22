package com.yunqi.common.config.handler;

import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.lang.Lang;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
@Order(100)
@RestControllerAdvice
public class GlobalException {

    /**
     * 通用异常处理方法
     * @param e 异常
     * @return  统一数据返回格式
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        // 打印堆栈信息
        log.error(Lang.getStackTrace(e));
        return Result.error(e.getMessage());
    }


    /**
     * 统一处理请求参数校验(普通传参)
     * @param e 异常
     * @return  统一数据返回格式
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public Object handleConstraintViolationException(Exception  e) {
        // 打印堆栈信息
        //log.error(Lang.getStackTrace(e));

        String errorMsg = "参数验证错误";
        BindingResult bindingResult = null;
        // 绑定效验出错时抛出
        if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            bindingResult = bindException.getBindingResult();
        }
        // 方法参数无效异常
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            bindingResult = methodArgumentNotValidException.getBindingResult();
        }
        // 规则校验出错时抛出
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
            errorMsg = constraintViolationException.getMessage();
        }
        if (bindingResult != null) {
            for (FieldError filedError : bindingResult.getFieldErrors()) {
                errorMsg = filedError.getDefaultMessage();
            }
        }

        return Result.error(ResultCode.FAILURE, errorMsg);
    }

    /**
     * 业务异常
     * @param e 异常
     * @return  统一数据返回格式
     */
    @ExceptionHandler(BizException.class)
    public Object handleBizException(BizException e) {

        // 打印堆栈信息
        log.error(Lang.getStackTrace(e));
        return Result.error(ResultCode.FAILURE, e.getMessage());
    }


}
