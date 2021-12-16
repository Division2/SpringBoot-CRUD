package com.boot.ex.services;

import com.boot.ex.exception.AleadyExistException;
import com.boot.ex.exception.ResourceNotFoundException;
import com.boot.ex.models.data.UserData;
import com.boot.ex.models.entities.User;
import com.boot.ex.models.requests.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.ex.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository repository;

	//회원 ID 조회
	@Transactional(readOnly = true)
	public UserData findByUserID(String userid) throws Exception {
		return repository.findByUserid(userid).orElseThrow(() -> new ResourceNotFoundException("요청한 리소스를 찾을 수 없습니다.")).getData();
	}

	private User save(UserRequest request) throws Exception {
		User result = repository.save(User.builder()
				.userid(request.getUserid())
				.password(passwordEncoder.encode(request.getPassword()))
				.name(request.getName())
				.phone(request.getPhone())
				.build());
		return result;
	}

	@Transactional
	public User insert(UserRequest request) throws Exception {

		Optional<User> user = repository.findByUserid(request.getUserid());

		if (user.isPresent()) {
			throw new AleadyExistException("이미 등록된 사용자입니다.");
		}

		return save(request);
	}

	@Transactional
	public User update(UserRequest request) throws Exception {

		Optional<User> user = repository.findByUserid(request.getUserid());

		if (user.isPresent()) {
			return save(request);
		}
		else {
			throw new ResourceNotFoundException("요청한 리소스를 찾을 수 없습니다.");
		}
	}
}