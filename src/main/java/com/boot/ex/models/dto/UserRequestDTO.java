package com.boot.ex.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class UserRequestDTO {

    /*
        NotNull, NotEmpty, NotBlank의 차이점
        NotNull : null만 비허용
        NotEmpty : null, "" 비허용
        NotBlank : null, "", " " 비허용
     */
    
    @NotBlank(message = "아이디가 입력되지 않았습니다.")
    private String userid;
    @NotBlank(message = "비밀번호가 입력되지 않았습니다.")
    private String password;
    @NotBlank(message = "이름이 입력되지 않았습니다.")
    @Size(min = 1, max = 10, message = "이름은 1~10자 사이로 입력해주세요.")
    private String name;
    @NotBlank(message = "전화번호가 입력되지 않았습니다.")
    private String phone;
}