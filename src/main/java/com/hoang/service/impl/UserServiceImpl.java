package com.hoang.service.impl;

import com.hoang.config.JwtTokenProvider;
import com.hoang.entity.User;
import com.hoang.exception.UserExistException;
import com.hoang.model.request.LoginRequest;
import com.hoang.model.request.SignUpRequest;
import com.hoang.repository.UserRepository;
import com.hoang.service.UserService;
import com.hoang.util.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User registerUser(SignUpRequest signUpRequest) {
        if(userRepository.existsUserByUsername(signUpRequest.getUsername())) {
            throw new UserExistException("Username is already taken!");
        }

        if(userRepository.existsUserByEmail(signUpRequest.getEmail())) {
            throw new UserExistException("Email Address already in use!");
        }

        // Creating user's account
        User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public Map<String, String> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

//        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, String> mapToken = new HashMap<>();
        mapToken.put("access-token", AppConstant.TOKEN_TYPE + " " + tokenProvider.generateAccessToken(authentication));
        mapToken.put("refresh-token", AppConstant.TOKEN_TYPE + " " + tokenProvider.generateRefreshToken(authentication));
        return mapToken;
    }
    
    @Override
    public String generateAccessToken(Authentication authentication) {
        return AppConstant.TOKEN_TYPE + " " + tokenProvider.generateAccessToken(authentication);
    }
}
