package com.snow.cs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JonSnow
 * @description 用户请求
 * @date 2022/10/10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/online-list")
    public Object getOnlineUsers() {

        // TODO 查询在线用户列表
        return null;
    }

    @PostMapping("/all-list")
    public Object getAllUsers() {
        // TODO 查询所有用户列表
        return null;
    }

    @PostMapping("/{uid}")
    public Object getUser(@PathVariable String uid) {
        // TODO 根据用户id获取用户信息
        return null;
    }
}
