package com.boot.ex.models.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonResponse<T> {

    private int status;
    private String message;
    private T data;

    public CommonResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}