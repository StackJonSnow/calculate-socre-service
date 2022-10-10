package com.snow.cs.exception;

import com.snow.cs.enums.ResponseCode;

/**
 * @author JonSnow
 * @description 业务异常
 * @date 2022/10/10
 */
public class BizException extends RuntimeException {
    private String code;
    private String msg;

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public static BizException error(ResponseCode responseCode) {
        String code = responseCode.getCode();
        String msg = responseCode.getMsg();
        return new BizException(code, msg);
    }

    public static BizException error(String code, String msg) {
        return new BizException(code, msg);
    }

    public static BizException error(String msg) {
        return new BizException(ResponseCode.SERVER_ERROR.getCode(), msg);
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
