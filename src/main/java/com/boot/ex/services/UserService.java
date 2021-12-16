package com.boot.ex.services;

import com.boot.ex.exception.ResourceNotFoundException;
import com.boot.ex.models.data.UserData;
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
	public UserData findByUserID(String userid) throws Exception {
		return repository.findByUserid(userid).orElseThrow(() -> new ResourceNotFoundException("요청한 리소스를 찾을 수 없습니다.")).getData();
	}
}