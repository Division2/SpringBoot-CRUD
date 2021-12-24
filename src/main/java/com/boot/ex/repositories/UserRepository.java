package com.boot.ex.repositories;

import java.util.List;
import java.util.Optional;

import com.boot.ex.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//전체 회원 조회
	List<User> findAll();

	//회원 ID 조회
	Optional<User> findByUserid(String userid);
}