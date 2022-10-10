package com.snow.cs.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.snow.cs.enums.MessageType;
import com.snow.cs.manager.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author JonSnow
 * @description 文本聊天处理
 * @date 2022/9/27
 */
@Component
public class TextChatWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private SessionManager sessionManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uid = (String) session.getAttributes().get("uid");
        sessionManager.register(uid, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String requestMsg  =  message.getPayload();
        JSONObject requestJSON = (JSONObject) JSON.parse(requestMsg);
        int type = requestJSON.getIntValue("type");
        MessageType.convert(type).send(sessionManager, requestJSON);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String uid = (String) session.getAttributes().get("uid");
        sessionManager.destroy(uid);
    }
}
