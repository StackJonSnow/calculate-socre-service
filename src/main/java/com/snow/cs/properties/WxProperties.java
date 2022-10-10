package com.snow.cs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JonSnow
 * @description WxProperties
 * @date 2022/10/10
 */
@Data
@ConfigurationProperties(prefix = "wx")
public class WxProperties {

    private String appId;

    private String appSecret;

    private String codeToSessionUrl;

    private String getUserInfoUrl;

    private String getAccessTokenUrl;
}
