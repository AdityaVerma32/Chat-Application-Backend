package com.ChatApplication.ChatApplication.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigure implements WebSocketMessageBrokerConfigurer {

    private final WebSocketInterceptor webSocketInterceptor;

    public WebSocketConfigure(WebSocketInterceptor webSocketInterceptor) {
        this.webSocketInterceptor = webSocketInterceptor;
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registery) {
        registery.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:5173")
                .addInterceptors(webSocketInterceptor)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registery) {
        registery.enableSimpleBroker("/topic");
        registery.setApplicationDestinationPrefixes("/app");
        registery.setUserDestinationPrefix("/user");
    }

}
