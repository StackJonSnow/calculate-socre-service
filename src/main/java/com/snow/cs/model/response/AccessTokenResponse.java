package com.snow.cs.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author JonSnow
 * @description TODO
 * @date 2022/10/10
 */
@Data
public class AccessTokenResponse extends WxResponse {

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private Integer expiresIn;
}
