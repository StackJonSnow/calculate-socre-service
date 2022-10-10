package com.snow.cs.service;

import com.snow.cs.model.response.AccessTokenResponse;
import com.snow.cs.model.response.CodeToSessionResponse;
import com.snow.cs.model.response.WxUserInfoResponse;
import com.snow.cs.properties.WxProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JonSnow
 * @description WX API
 * @date 2022/10/10
 */
@Service
public class WxApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxProperties wxProperties;

    /**
     * 根据 js code 获取 openid
     * @param code
     * @return
     */
    public CodeToSessionResponse getWxSessionByCode(String code) {
        Map<String,String> param = new HashMap<String,String>();
        param.put("appid", wxProperties.getAppId());
        param.put("secret", wxProperties.getAppSecret());
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        return restTemplate.postForObject(wxProperties.getCodeToSessionUrl(), param, CodeToSessionResponse.class);
    }

    /**
     * 获取 access_token
     * @return
     */
    public AccessTokenResponse getAccessToken() {
        String getTokenUrl = String.format(wxProperties.getGetAccessTokenUrl(), wxProperties.getAppId()
                , wxProperties.getAppSecret());
        return restTemplate.getForObject(getTokenUrl, AccessTokenResponse.class);
    }

    /**
     * 获取微信用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    public WxUserInfoResponse getUserInfo(String accessToken, String openId) {
        String url = String.format(wxProperties.getGetUserInfoUrl(), accessToken);
        Map<String, Object> param = new HashMap<>();
        param.put("openid", openId);
        return restTemplate.postForObject(url, param, WxUserInfoResponse.class);
    }
}
