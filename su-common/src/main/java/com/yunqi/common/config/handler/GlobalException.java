package com.yunqi.common.config.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.yunqi.common.base.result.Result;
import com.yunqi.common.base.result.ResultCode;
import com.yunqi.common.config.exception.BizException;
import com.yunqi.starter.common.lang.Lang;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * <p>
 *     全局异常处理
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
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


    /**
     * 无此权限
     * @param e 异常
     * @return 	统一数据返回格式
     */
    @ExceptionHandler(NotPermissionException.class)
    public Object NotPermissionException(NotPermissionException e) {
        // 打印堆栈信息
        //log.error(Lang.getStackTrace(e));
        return Result.error(ResultCode.USER_NOT_PERMISSION);
    }


    /**
     * 权限异常
     * @param e 异常
     * @return 	统一数据返回格式
     */
    @ExceptionHandler(NotLoginException.class)
    public Object handlerNotLoginException(NotLoginException e) {

        // 打印堆栈信息
        // log.error(Lang.getStackTrace(e));

        // 判断场景值，定制化异常信息
        ResultCode resultCode;

        if(e.getType().equals(NotLoginException.NOT_TOKEN)) {
            resultCode = ResultCode.USER_NOT_LOGIN;
        }
        else if(e.getType().equals(NotLoginException.INVALID_TOKEN)) {
            resultCode = ResultCode.USER_LOGIN_INVALID;
        }
        else if(e.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            resultCode = ResultCode.USER_LOGIN_INVALID;
        }
        else if(e.getType().equals(NotLoginException.BE_REPLACED)) {
            resultCode = ResultCode.USER_LOGIN_BE_REPLACED;
        }
        else if(e.getType().equals(NotLoginException.KICK_OUT)) {
            resultCode = ResultCode.USER_LOGIN_BE_REPLACED;
        }
        else {
            resultCode = ResultCode.USER_NOT_LOGIN;
        }

        // 返回给前端
        return Result.error(resultCode);
    }
}
