package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UnLockAccForm;

public interface unlockAccRepository extends JpaRepository<UnLockAccForm, Integer> {

}
