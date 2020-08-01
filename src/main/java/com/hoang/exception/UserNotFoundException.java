package com.hoang.exception;

import com.hoang.model.BusinessErrorCode;
import com.hoang.model.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    private static final BusinessErrorCode ERROR_CODE = ErrorCode.USER_NOT_FOUND;

    public UserNotFoundException(String message) {
        super(message);
    }
}
