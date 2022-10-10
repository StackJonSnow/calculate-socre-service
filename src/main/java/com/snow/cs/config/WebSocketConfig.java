package com.snow.cs.config;

import com.snow.cs.handler.TextChatWebSocketHandler;
import com.snow.cs.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author JonSnow
 * @description WebSocketConfig
 * @date 2022/9/27
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private TextChatWebSocketHandler textChatWebSocketHandler;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //  为指定的URL 配置具体的 WebSocketHandler
        webSocketHandlerRegistry.addHandler(textChatWebSocketHandler, getChatHandlerPath())
                .addInterceptors(authInterceptor)
                .setAllowedOrigins("*");
    }

    private String getChatHandlerPath() {
        return "/chat/{uid}/{token}";
    }
}
