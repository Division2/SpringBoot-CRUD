package com.boot.ex.models.responses;

public class UserResponse<T> extends CommonResponse<T> {

    public UserResponse(int status, String message, T data) {
        super(status, message, data);
    }
}