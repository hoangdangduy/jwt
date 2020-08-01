package com.hoang.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResultResponse<T> {
    private T data;
    private String msg;
    private Integer code;
}
