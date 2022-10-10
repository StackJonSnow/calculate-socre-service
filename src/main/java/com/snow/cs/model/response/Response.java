package com.snow.cs.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.snow.cs.enums.ResponseCode;
import lombok.Data;

/**
 * @author JonSnow
 * @description 统一响应
 * @date 2022/10/10
 */
@Data
public class Response<T> {

    private String code;
    private String msg;
    private T data;

    public Response() {
    }

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Response(T data) {
        this.data = data;
        this.code = ResponseCode.SUCCESS.getCode();
        this.msg = ResponseCode.SUCCESS.getMsg();
    }


    @JSONField(
            serialize = false
    )
    public boolean isSuccess() {
        return ResponseCode.SUCCESS.getCode().equals(this.code);
    }

    public static <T> Response<T> success(T data) {
        return new Response(data);
    }

    public static Response<Object> success() {
        return new Response(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
    }

    public static Response<Object> error(ResponseCode responseCode) {
        return new Response(responseCode.getCode(), responseCode.getMsg());
    }

    public static Response<Object> fail(ResponseCode responseCode) {
        return new Response(responseCode.getCode(), responseCode.getMsg());
    }

    public static Response<Object> error(String code, String msg) {
        return new Response(code, msg);
    }
}
