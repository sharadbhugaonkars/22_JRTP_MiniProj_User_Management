package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LoginForm;

public interface LoginRepository extends JpaRepository<LoginForm, Integer> {

}
