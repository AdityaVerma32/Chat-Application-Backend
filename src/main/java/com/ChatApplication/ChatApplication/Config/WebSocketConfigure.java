package com.ChatApplication.ChatApplication.Config; // Package declaration for WebSocket configuration

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocketConfigure is a configuration class that enables WebSocket
 * communication using STOMP (Simple Text Oriented Messaging Protocol).
 * This allows real-time messaging between connected clients.
 */
@Configuration // Marks this class as a Spring configuration component
@EnableWebSocketMessageBroker // Enables WebSocket message handling with STOMP
public class WebSocketConfigure implements WebSocketMessageBrokerConfigurer {

    private final WebSocketInterceptor webSocketInterceptor;

    /**
     * Constructor-based dependency injection for WebSocketInterceptor.
     * This interceptor can be used to handle authentication and logging
     * for WebSocket connections.
     *
     * @param webSocketInterceptor Custom interceptor for WebSocket requests.
     */
    public WebSocketConfigure(WebSocketInterceptor webSocketInterceptor) {
        this.webSocketInterceptor = webSocketInterceptor;
    }

    /**
     * Registers STOMP (WebSocket) endpoints that clients can connect to.
     * This method allows WebSocket connections from a specified origin
     * and supports fallback options using SockJS.
     *
     * @param registery The registry to add WebSocket endpoints.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registery) {
        registery.addEndpoint("/ws") // WebSocket endpoint clients will connect to
                .setAllowedOrigins("http://localhost:5173") // Restricts connections to this frontend origin
                .addInterceptors(webSocketInterceptor) // Adds an interceptor for authentication/logging
                .withSockJS(); // Enables SockJS fallback for clients that do not support WebSockets
    }

    /**
     * Configures the message broker that handles routing of messages between clients.
     *
     * @param registery The registry for message broker configurations.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registery) {
        registery.enableSimpleBroker("/topic"); // Enables a simple in-memory message broker for topics (public chat)
        registery.setApplicationDestinationPrefixes("/app"); // Prefix for messages sent from clients to the server
        registery.setUserDestinationPrefix("/user"); // Prefix for user-specific private messages
    }
}
