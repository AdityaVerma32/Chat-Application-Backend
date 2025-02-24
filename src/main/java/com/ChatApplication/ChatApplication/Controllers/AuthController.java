package com.ChatApplication.ChatApplication.Controllers; // Package declaration for authentication controller

import com.ChatApplication.ChatApplication.DTO.LoginUser;
import com.ChatApplication.ChatApplication.Model.UserModel;
import com.ChatApplication.ChatApplication.Services.UserService;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * AuthController handles authentication-related operations such as
 * user registration and login.
 */
@Controller // Marks this class as a Spring MVC controller
public class AuthController {

    private final UserService userService; // Dependency on UserService for handling authentication logic

    /**
     * Constructor-based dependency injection for UserService.
     *
     * @param userService Service responsible for user authentication and registration.
     */
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles user registration requests.
     *
     * @param userModel The user details received in the request body.
     * @return ResponseEntity containing ApiResponse with registration status.
     */
    @PostMapping("/register") // Maps HTTP POST requests to "/register"
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserModel userModel) {
        return userService.regsiterUser(userModel); // Calls service method to register the user
    }

    /**
     * Handles user login requests.
     *
     * @param loginUser The login credentials received in the request body.
     * @return ResponseEntity containing ApiResponse with login status.
     */
    @PostMapping("/login") // Maps HTTP POST requests to "/login"
    public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginUser loginUser) {
        return userService.loginUser(loginUser); // Calls service method to authenticate the user
    }
}
