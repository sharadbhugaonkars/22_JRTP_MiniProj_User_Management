package com.example.demo.repo;

import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	// select * from user where email = ?
	public User findByEmail(String email);

	// select * from user where email = ? And password = ?
	public User findByEmailAndPassword(String email, String pwd);

}
