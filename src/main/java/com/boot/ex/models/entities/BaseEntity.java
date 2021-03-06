package com.boot.ex.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

//Getter, Setter 설정
@Getter @Setter
//MappedSuperclass 설정 시 테이블과 매핑되지 않고 매핑 정보를 상속하기 위해 사용되어 단독으로 사용할 일이 없기 떄문에 abstract 설정 해주어야 함
@MappedSuperclass
public abstract class BaseEntity {

    /*
        @Id - 기본 키 설정
        @GeneratedValue - 기본 키의 AUTO_INCREMENT 설정
        @Column -
            name : 컬럼 이름 매핑
            insertable : Insert 시점에서 적용/미적용 설정
            updatable : Update 시점에서 적용/미적용 설정
            nullable : 컬럼 nullable 설정
            columnDefinition : 컬럼의 기본값 지정
        @NotNull - nullable과 DDL 시 테이블에 not null을 명시해주지만 데이터에 null이 들어오지 못 하도록 유효성 검사까지 해준다.
     */

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createAt;

    @Column(name = "update_at", insertable = false, columnDefinition = "TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
    private Date updateAt;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean deleted;

    //Insert 시점에서 같이 실행할 메소드(PrePersist)
    @PrePersist
    protected void onCreate() {
        this.createAt = Timestamp.valueOf(LocalDateTime.now());
    }

    //Update 시점에서 같이 실행할 메소드(PreUpdate)
    @PreUpdate
    protected void onUpdate() {
        this.updateAt = Timestamp.valueOf(LocalDateTime.now());
    }

    //객체 얻어올 추상메소드
    public abstract Object getData();
}