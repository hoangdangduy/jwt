package com.hoang.service;

import com.hoang.entity.User;
import com.hoang.model.request.LoginRequest;
import com.hoang.model.request.SignUpRequest;

public interface UserService {
    User registerUser(SignUpRequest signUpRequest);

    String authenticateUser(LoginRequest loginRequest);
}
