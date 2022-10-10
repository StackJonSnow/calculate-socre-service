package com.snow.cs.service;

import com.snow.cs.dao.entity.User;
import com.snow.cs.dao.service.IUserService;
import com.snow.cs.enums.ResponseCode;
import com.snow.cs.exception.BizException;
import com.snow.cs.model.response.AccessTokenResponse;
import com.snow.cs.model.response.CodeToSessionResponse;
import com.snow.cs.model.response.WxUserInfoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author JonSnow
 * @description 登录服务
 * @date 2022/10/10
 */
@Service
public class LoginService {

    @Autowired
    private WxApiService wxApiService;

    @Autowired
    private IUserService userService;

    public void login(String code) {
        CodeToSessionResponse wxSession = wxApiService.getWxSessionByCode(code);
        if (!wxSession.requestSuccess()) {
            throw BizException.error(ResponseCode.BIZ_ERROR);
        }

        User user = userService.findUserByOpenid(wxSession.getOpenid());
        if (Objects.nonNull(user)) {
            return;
        }

        AccessTokenResponse accessToken = wxApiService.getAccessToken();
        WxUserInfoResponse wxUser = wxApiService.getUserInfo(accessToken.getAccessToken(), wxSession.getOpenid());
        User newUser = new User();
        newUser.setOpenId(wxSession.getOpenid());
        BeanUtils.copyProperties(wxUser, newUser);
        userService.save(newUser);

    }
}
