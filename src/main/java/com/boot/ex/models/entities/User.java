package com.boot.ex.models.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//생성자 접근 제한을 Protected 레벨로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//User Entity 클래스
@Entity
//User Entity의 매핑 테이블 명 account로 설정
@Table(name = "account")
public class User extends BaseEntity {

    @Column(name = "userid", unique = true, nullable = false, length = 50)
    private String userid;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Builder
    public User(String userid, String password, String name, String phone) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    //응답 객체 get
    @Override
    public Object getObject() {
        return null;
    }
}
