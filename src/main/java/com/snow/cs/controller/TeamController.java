package com.snow.cs.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JonSnow
 * @description 组队相关请求
 * @date 2022/10/10
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    @PostMapping("/create")
    public void createRoom() {
        //TODO  创建房间
    }

    @PostMapping("/join")
    public void joinRoom() {
        //TODO  进入房间
    }

    @PostMapping("/quit")
    public void quitRoom() {
        //TODO  退出房间
    }


    @PostMapping("/destroy")
    public void destroyRoom() {
        //TODO  销毁房间
    }
}
