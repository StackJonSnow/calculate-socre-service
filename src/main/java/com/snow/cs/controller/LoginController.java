package com.snow.cs.controller;

import com.snow.cs.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JonSnow
 * @description 登录请求
 * @date 2022/10/10
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/wx/code")
    public void loginByWxCode(String code) {
        loginService.login(code);
    }
}
