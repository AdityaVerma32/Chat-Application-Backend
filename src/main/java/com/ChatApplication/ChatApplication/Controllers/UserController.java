package com.ChatApplication.ChatApplication.Controllers;

import com.ChatApplication.ChatApplication.DTO.LoginUser;
import com.ChatApplication.ChatApplication.Services.UserService;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * UserController handles user-related operations such as fetching the list of users.
 */
@Controller // Marks this class as a Spring MVC controller
public class UserController {

    private final UserService userService; // Service for handling user-related operations

    /**
     * Constructor to inject UserService dependency.
     *
     * @param userService Service to handle user-related logic.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all registered users.
     * This may be used to display available users for chat.
     *
     * @param currrentUser The currently logged-in user making the request.
     * @return ResponseEntity containing the list of users wrapped in an ApiResponse.
     */
    @PostMapping("/getAllUsers") // Maps HTTP POST request to fetch all users
    public ResponseEntity<ApiResponse> getAllUsers(@RequestBody LoginUser currrentUser) {
        return userService.getAllUsers(currrentUser); // Calls service to get user list
    }
}
