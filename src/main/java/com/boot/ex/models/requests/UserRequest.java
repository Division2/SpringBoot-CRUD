package com.boot.ex.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {

    private String userid;
    private String password;
    private String name;
    private String phone;
}