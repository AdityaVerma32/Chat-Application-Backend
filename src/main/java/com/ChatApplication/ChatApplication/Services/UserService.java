package com.ChatApplication.ChatApplication.Services;

import com.ChatApplication.ChatApplication.DTO.LoginUser;
import com.ChatApplication.ChatApplication.Model.UserModel;
import com.ChatApplication.ChatApplication.Repository.UserRepo;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepo userRepo, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<ApiResponse> regsiterUser(UserModel userModel) {
        userModel.setPassword("NA");
        userRepo.save(userModel);
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "User Registration Successfull",
                        null),
                HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> loginUser(LoginUser loginUser, HttpServletRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getEmail(), "NA")
            );
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            UserModel userModel = userRepo.findByEmail(loginUser.getEmail());

            // Create session explicitly
            HttpSession session = request.getSession(true);

            // Store security context in session
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            UserModel userModel = userRepo.findByEmail(loginUser.getEmail());

            return new ResponseEntity<>(
                    new ApiResponse(
                            true,
                            "User Logged In",
                            userModel),
                    HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<ApiResponse> getAllUsers(LoginUser loginUser) {
        System.out.println("Email: " + loginUser.getEmail());
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
