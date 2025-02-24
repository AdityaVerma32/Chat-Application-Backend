package com.ChatApplication.ChatApplication.Config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.util.Map;

@Component
public class WebSocketInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // Extract email from request (Assume it's passed as a query param)
        // Extract email from query params instead of headers
        URI uri = request.getURI();
        String query = uri.getQuery(); // Get query string (e.g., "user-email=aditya@gmail.com")

        if (query != null && query.contains("user-email=")) {
            String email = query.split("user-email=")[1]; // Extract email
//            System.out.println("User Connected: " + email);
            attributes.put("email", email); // Store email in session attributes
        } else {
            System.out.println("No email found in query params");
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
