package com.boot.ex.models.responses;

import com.boot.ex.models.dto.UserResponseDTO;

public class UserResponse extends CommonResponse<UserResponseDTO> {

    public UserResponse(int status, String message, UserResponseDTO data) {
        super(status, message, data);
    }
}