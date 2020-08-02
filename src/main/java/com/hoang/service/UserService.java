package com.hoang.service;

import com.hoang.entity.User;
import com.hoang.model.request.LoginRequest;
import com.hoang.model.request.SignUpRequest;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface UserService {
    User registerUser(SignUpRequest signUpRequest);

    Map<String, String> authenticateUser(LoginRequest loginRequest);
    
    String generateAccessToken(Authentication authentication);
}
