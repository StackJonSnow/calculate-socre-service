package com.snow.cs.interceptor;

import com.google.common.base.Splitter;
import com.snow.cs.manager.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

/**
 * @author JonSnow
 * @description 认证拦截，校验token是否有效
 * @date 2022/9/27
 */
@Component
public class AuthInterceptor implements HandshakeInterceptor {

    @Autowired
    private SessionManager sessionManager;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println("开始握手...");
        String path = serverHttpRequest.getURI().getPath();
        List<String> paths = Splitter.on("/")
                .trimResults()
                .splitToList(path);
        if (paths.size() < 2) {
            return false;
        }
        String token = paths.get(paths.size() - 1);
        String uid = paths.get(paths.size() - 2);
        map.put("uid", uid);

        // TODO 验证token有效性

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("结束握手...");
    }
}
