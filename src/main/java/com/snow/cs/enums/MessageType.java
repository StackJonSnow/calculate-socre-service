package com.snow.cs.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.snow.cs.manager.SessionManager;
import com.snow.cs.model.SingleMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Objects;

/**
 * @author JonSnow
 * @description 消息类型
 * @date 2022/10/10
 */
@Slf4j
public enum MessageType {

    SINGLE(1) {
        @Override
        public void send(SessionManager sessionManager, JSONObject msg) {
            log.info("收到单聊消息:{}", msg.toJSONString());
            SingleMessage singleMessage = JSON.toJavaObject(msg, SingleMessage.class);
            String toUid = singleMessage.getToUid();
            if (Objects.isNull(toUid)) {
                return;
            }
            WebSocketSession session = sessionManager.getSession(toUid);
            if (Objects.isNull(session)) {
                return;
            }
            try {
                session.sendMessage(new TextMessage(msg.toJSONString()));
            } catch (IOException e) {
                e.printStackTrace();
                // TODO 日志
            }
        }
    },
    GROUP(2) {
        @Override
        public void send(SessionManager sessionManager, JSONObject msg) {
            log.info("收到群聊消息:{}", msg.toJSONString());

            // TODO 群发逻辑
        }
    };

    private int type;

    MessageType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static MessageType convert(int type) {
        for (MessageType messageType : values()) {
            if (messageType.getType() == type) {
                return messageType;
            }
        }
        // TODO 抛异常
        return null;
    }

    public abstract void send(SessionManager sessionManager, JSONObject msg);
}
