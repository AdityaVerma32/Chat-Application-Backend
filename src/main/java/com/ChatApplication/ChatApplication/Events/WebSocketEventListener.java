package com.ChatApplication.ChatApplication.Events;

import com.ChatApplication.ChatApplication.Model.UserModel;
import com.ChatApplication.ChatApplication.Repository.UserRepo;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
public class WebSocketEventListener {

    private final UserRepo userRepo;

    public WebSocketEventListener(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String email = headerAccessor.getFirstNativeHeader("email");

        if (email != null) {
            UserModel user = userRepo.findByEmail(email);
            if (user != null) {
                user.setOnline(true);
                userRepo.save(user);
            }
        }
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String email = headerAccessor.getFirstNativeHeader("email");
        if (email != null) {
            UserModel user = userRepo.findByEmail(email);
            if (user != null) {
                user.setOnline(false);
                userRepo.save(user);
            }
        }
    }
}
