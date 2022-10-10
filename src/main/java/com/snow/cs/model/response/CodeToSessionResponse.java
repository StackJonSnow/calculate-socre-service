package com.snow.cs.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author JonSnow
 * @description code 获取 session 响应
 * @date 2022/10/10
 */
@Data
public class CodeToSessionResponse extends WxResponse {

    @JSONField(name = "session_key")
    private String sessionKey;

    private String openid;

    private String unionid;
}
