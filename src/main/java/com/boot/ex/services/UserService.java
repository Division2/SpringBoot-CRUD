package com.boot.ex.services;

import com.boot.ex.models.data.UserData;
import com.boot.ex.models.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.boot.ex.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository repository;

	//회원 ID 조회
	@Transactional(readOnly = true)
	public UserData findByUserID(String userid) {
		return repository.findByUserid(userid).orElse(null).getObject();
	}
}