package com.hoang.controller;

import com.hoang.model.request.LoginRequest;
import com.hoang.model.request.SignUpRequest;
import com.hoang.model.response.ResultResponse;
import com.hoang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResultResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        ResultResponse<Map<String, String>> resultResponse = new ResultResponse<>();
        resultResponse.setData(userService.authenticateUser(loginRequest));
        resultResponse.setMsg("User login successfully");
        resultResponse.setCode(HttpStatus.OK.value());

        return ResponseEntity.ok(resultResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResultResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        userService.registerUser(signUpRequest);
        return ResponseEntity.ok(new ResultResponse<Boolean>(true, "User registered successfully", HttpStatus.OK.value()));
    }
    
    @GetMapping("/gen-access-token")
    public ResponseEntity<ResultResponse> genToken() {
        return ResponseEntity.ok(new ResultResponse<Boolean>(true,
                                                                userService.generateAccessToken(SecurityContextHolder.getContext().getAuthentication()),
                                                                HttpStatus.OK.value()));
    }
}
