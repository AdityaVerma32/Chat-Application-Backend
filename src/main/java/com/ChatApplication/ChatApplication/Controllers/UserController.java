package com.ChatApplication.ChatApplication.Controllers;

import com.ChatApplication.ChatApplication.DTO.LoginUser;
import com.ChatApplication.ChatApplication.Services.UserService;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/getAllUsers")
    public ResponseEntity<ApiResponse> getAllUsers(@RequestBody LoginUser currrentUser){
        return userService.getAllUsers(currrentUser);
    }

}
