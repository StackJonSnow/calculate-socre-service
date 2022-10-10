package com.snow.cs.model;

import lombok.Data;

/**
 * @author JonSnow
 * @description 单聊消息
 * @date 2022/10/10
 */
@Data
public class SingleMessage extends Message {

    private String fromUid;

    private String toUid;

    private String content;
}
