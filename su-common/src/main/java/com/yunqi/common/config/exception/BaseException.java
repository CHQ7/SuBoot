package com.yunqi.common.config.exception;

import com.yunqi.common.base.result.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *     异常基类
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer code;
    /**
     * 错误信息
     */
    protected String message;

    /**
     * 有参构造函数
     * @param resultCode 状态码枚举类
     */
    public BaseException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }

    /**
     * 有参构造函数
     * @param message 错误信息
     */
    public BaseException(String message) {
        this.code = ResultCode.FAILURE.code;
        this.message = message;
    }

    /**
     * 全参构造函数
     * @param code  错误码
     * @param message   错误信息
     */
    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
