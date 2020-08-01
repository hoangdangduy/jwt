package com.hoang.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class BusinessErrorCode {
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
