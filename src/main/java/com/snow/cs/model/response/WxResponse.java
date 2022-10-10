package com.snow.cs.model.response;

import lombok.Data;

import java.util.Objects;

/**
 * @author JonSnow
 * @description WX接口响应参数
 * @date 2022/10/10
 */
@Data
public class WxResponse {

    private Integer errcode;

    private String errmsg;

    public boolean requestSuccess() {
        return Objects.equals(errcode, 0);
    }
}
