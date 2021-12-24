package com.boot.ex.models.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDTO {

    private final Long aid;
    private final String userid;
    private final String name;
    private final String phone;

    @Builder
    public UserResponseDTO(Long aid, String userid, String name, String phone) {
        this.aid = aid;
        this.userid = userid;
        this.name = name;
        this.phone = phone;
    }
}