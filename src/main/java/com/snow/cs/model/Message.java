package com.snow.cs.model;

import lombok.Data;

/**
 * @author JonSnow
 * @description 消息
 * @date 2022/10/10
 */
@Data
public class Message {

    private long timestamp;

    /**
     * 类型：1-单发消息，2-群聊消息
     */
    private int type;

    /**
     * 样式：1-文本，2-图片，3-音频，4-视频
     */
    private int style;
}
