package com.yunqi.common.config.exception;

import com.yunqi.common.base.result.ResultCode;
import lombok.Getter;

/**
 * <p>
 *     业务异常
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Getter
public class BizException extends BaseException{

    /**
     * 有参构造函数
     * @param resultCode 状态码枚举类
     */
    public BizException(ResultCode resultCode) {
        super(resultCode);
    }

    /**
     * 有参构造函数
     * @param message 错误信息
     */
    public BizException(String message) {
        super(ResultCode.FAILURE.code, message);
    }

    /**
     * 全参构造函数
     * @param code  错误码
     * @param message   错误信息
     */
    public BizException(Integer code, String message) {
        super(code, message);
    }
}
