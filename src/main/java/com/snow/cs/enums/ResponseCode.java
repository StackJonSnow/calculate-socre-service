package com.snow.cs.enums;

/**
 * @author JonSnow
 * @description 响应码
 * @date 2022/10/10
 */
public enum ResponseCode {

    SUCCESS("200", "success"),
    PARAMS_ERROR("300", "必传参数为空"),
    PARAMS_FORMAT_ERROR("301", "参数格式错误"),
    BIZ_ERROR("400", "业务错误"),
    SERVER_ERROR("500", "服务器异常");

    private String code;
    private String msg;

    private ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
