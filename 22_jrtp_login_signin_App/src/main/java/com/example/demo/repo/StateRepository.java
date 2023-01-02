package com.example.demo.repo;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.State;


public interface StateRepository extends JpaRepository<State, Integer> {
	
		
	@Query("Select s.stateId,s.stateNames from State s where s.countryId=:id")
	public  List<Object[]> fetchStateIdAndName(Integer id);

}
