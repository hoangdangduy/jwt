package com.hoang.model;

import org.springframework.http.HttpStatus;

public class ErrorCode {
    public static final BusinessErrorCode USER_NOT_FOUND = new BusinessErrorCode(500001, "User not found", HttpStatus.NOT_FOUND);
    public static final BusinessErrorCode USER_EXIST = new BusinessErrorCode(503, "User exist", HttpStatus.BAD_REQUEST);
}
