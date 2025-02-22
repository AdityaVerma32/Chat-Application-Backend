package com.ChatApplication.ChatApplication.Services;

import com.ChatApplication.ChatApplication.Model.UserModel;
import com.ChatApplication.ChatApplication.Repository.UserRepo;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<ApiResponse> regsiterUser(UserModel userModel) {
        userRepo.save(userModel);
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "User Registration Successfull",
                        null),
                HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> loginUser(String email) {
        UserModel userModel = userRepo.findByEmail(email);
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "User Logged In",
                        userModel),
                HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> getAllUsers() {
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "List of Users",
                        userRepo.findAll()
                ),
                HttpStatus.OK
        );
    }
}
