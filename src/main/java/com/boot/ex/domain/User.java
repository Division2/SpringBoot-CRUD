package com.boot.ex.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class User {
	
	@Entity
	@Getter
	@NoArgsConstructor
	@Table(name = "account")
	public static class UserEntity {
		@Id
		@Column(name = "aid")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long aid;
		
		@Column(name = "userid")
		private String userid;
		
		@Column(name = "password")
		private String password;
		
		@Column(name = "name")
		private String name;
		
		@Column(name = "phone")
		private String phone;
		
		@Builder
		public UserEntity(Long aid, String userid, String password, String name, String phone) {
			this.aid = aid;
			this.userid = userid;
			this.password = password;
			this.name = name;
			this.phone = phone;
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class RequestDTO {
		private Long aid;
		private String userid;
		private String password;
		private String name;
		private String phone;
		
		@Builder
		public RequestDTO(UserEntity entity) {
			this.aid = entity.aid;
			this.userid = entity.userid;
			this.password = entity.password;
			this.name = entity.name;
			this.phone = entity.phone;
		}
		
		public UserEntity toEntity() {
			return UserEntity.builder()
					.aid(aid)
					.userid(userid)
					.password(password)
					.name(name)
					.phone(phone)
					.build();
		}
	}
	
	@Getter
	@AllArgsConstructor
	public static class ResponseDTO {
		private int status;
		private String message;
		private Object date;
		private UserEntity data;
	}
	
	@Getter
	@AllArgsConstructor
	public static class ResponseDTO2 {
		private int status;
		private String message;
		private Object date;
	}
	
	@Getter
	@AllArgsConstructor
	public static class ResponseDTO3 {
		private int status;
		private String message;
		private Object date;
		private List<UserEntity> data;
	}
}