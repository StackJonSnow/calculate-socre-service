package com.snow.cs.manager;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JonSnow
 * @description 管理会话
 * @date 2022/9/27
 */
@Component
public class SessionManager {

    private final Map<String, WebSocketSession> SESSION_MAP = new ConcurrentHashMap<>();

    public void register(String uid, WebSocketSession session) {
        SESSION_MAP.put(uid, session);
    }

    public void destroy(String uid) {
        SESSION_MAP.remove(uid);
    }

    public Set<String> getOnlineUids() {
        return SESSION_MAP.keySet();
    }

    public boolean isOnline(String uid) {
        return SESSION_MAP.containsKey(uid);
    }

    public WebSocketSession getSession(String uid) {
        return SESSION_MAP.get(uid);
    }
}
