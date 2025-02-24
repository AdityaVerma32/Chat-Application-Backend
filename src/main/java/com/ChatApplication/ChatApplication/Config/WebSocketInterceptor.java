package com.ChatApplication.ChatApplication.Config; // Package declaration for WebSocket configuration

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.util.Map;

/**
 * WebSocketInterceptor is a custom interceptor for handling WebSocket handshake requests.
 * It extracts the user's email from the query parameters and stores it in session attributes.
 * This is useful for identifying users during WebSocket communication.
 */
@Component // Marks this class as a Spring-managed component
public class WebSocketInterceptor implements HandshakeInterceptor {

    /**
     * Intercepts the WebSocket handshake request before the connection is established.
     * Extracts the user's email from the query parameters and stores it in session attributes.
     *
     * @param request     The incoming HTTP request for the WebSocket handshake.
     * @param response    The HTTP response associated with the handshake.
     * @param wsHandler   The WebSocket handler managing the connection.
     * @param attributes  A map of attributes that can be stored in the session.
     * @return            true to continue the handshake process, false to reject it.
     * @throws Exception  If any error occurs during processing.
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // Get the URI of the request
        URI uri = request.getURI();
        String query = uri.getQuery(); // Extracts the query string (e.g., "user-email=aditya@gmail.com")

        // Check if the query contains the "user-email" parameter
        if (query != null && query.contains("user-email=")) {
            // Extract email from the query parameter
            String email = query.split("user-email=")[1]; // Retrieves email after "user-email="
            System.out.println("User Connected: " + email);

            // Store email in session attributes for use in WebSocket sessions
            attributes.put("email", email);
        } else {
            System.out.println("No email found in query params");
        }

        return true; // Continue with the handshake
    }

    /**
     * Executes after the WebSocket handshake is complete.
     * This method is currently unused but can be used for post-handshake processing.
     *
     * @param request   The original handshake request.
     * @param response  The handshake response.
     * @param wsHandler The WebSocket handler.
     * @param exception Any exception that occurred during the handshake.
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // No post-handshake processing required for now
    }
}
