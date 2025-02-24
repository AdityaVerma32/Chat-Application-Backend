package com.ChatApplication.ChatApplication.Services;

import com.ChatApplication.ChatApplication.DTO.LoginUser;
import com.ChatApplication.ChatApplication.Model.UserModel;
import com.ChatApplication.ChatApplication.Repository.UserRepo;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling user-related operations.
 * This includes user registration, login, and retrieving users.
 */
@Service
public class UserService {

    private final UserRepo userRepo;

    /**
     * Constructor-based dependency injection for the UserRepo repository.
     *
     * @param userRepo Repository for performing CRUD operations on UserModel.
     */
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Registers a new user in the system.
     *
     * @param userModel The user data to be saved.
     * @return ResponseEntity containing an ApiResponse indicating success or failure.
     */
    public ResponseEntity<ApiResponse> regsiterUser(UserModel userModel) {
        userRepo.save(userModel);
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "User Registration Successful",
                        null),
                HttpStatus.OK);
    }

    /**
     * Handles user login by checking if the email exists in the database.
     *
     * @param loginUser DTO containing the email of the user attempting to log in.
     * @return ResponseEntity with an ApiResponse indicating login success or failure.
     */
    public ResponseEntity<ApiResponse> loginUser(LoginUser loginUser) {
        UserModel userModel = userRepo.findByEmail(loginUser.getEmail());
        if (userModel == null) {
            return new ResponseEntity<>(
                    new ApiResponse(
                            false,
                            "User does not exist",
                            null
                    ),
                    HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(
                    new ApiResponse(
                            true,
                            "User Logged In",
                            userModel),
                    HttpStatus.OK);
        }
    }

    /**
     * Retrieves a list of all users except the currently logged-in user.
     * This is useful for displaying available users for chat.
     *
     * @param loginUser DTO containing the email of the current user.
     * @return ResponseEntity with a list of users except the currently logged-in user.
     */
    public ResponseEntity<ApiResponse> getAllUsers(LoginUser loginUser) {
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "List of Users",
                        userRepo.findByEmailNot(loginUser.getEmail())
                ),
                HttpStatus.OK
        );
    }
}
