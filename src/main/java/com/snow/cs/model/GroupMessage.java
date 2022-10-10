package com.snow.cs.model;

import lombok.Data;

/**
 * @author JonSnow
 * @description 群聊消息
 * @date 2022/10/10
 */
@Data
public class GroupMessage extends Message {

    private String uid;

    private String groupId;

    private String content;
}
