package com.hoang.exception;

import com.hoang.model.BusinessErrorCode;
import com.hoang.model.ErrorCode;
import lombok.Getter;

@Getter
public class UserExistException extends RuntimeException{
    private static final BusinessErrorCode ERROR_CODE = ErrorCode.USER_EXIST;

    public UserExistException(String message) {
        super(message);
    }
}
