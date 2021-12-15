package com.boot.ex.models.responses;

import com.boot.ex.models.data.UserData;

public class UserResponse extends CommonResponse<UserData> {

    public UserResponse(int status, String message, UserData data) {
        super(status, message, data);
    }
}