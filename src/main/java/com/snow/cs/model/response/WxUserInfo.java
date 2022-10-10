package com.snow.cs.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author JonSnow
 * @description TODO
 * @date 2022/10/10
 */
@Data
public class WxUserInfo {

    @JSONField(name = "union_id")
    private String unionId;

    @JSONField(name = "nick_name")
    private String nickName;

    private String mobile;

    private String birthday;

    private String avatar;

    private String email;

    private Integer sex;

    private String country;

    @JSONField(name = "auth_at")
    private Long authAt;

}
