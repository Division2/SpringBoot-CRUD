package com.boot.ex.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.ex.models.User;
import com.boot.ex.models.User.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<User.UserEntity, Long> {
	
	Optional<UserEntity> findByUserid(String userid);
}