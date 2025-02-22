package com.ChatApplication.ChatApplication.Controllers;

import com.ChatApplication.ChatApplication.Services.UserService;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse> getAllUsers(){
        return userService.getAllUsers();
    }

}
