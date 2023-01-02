package com.example.demo.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	
	
	@Query("Select c.cityId,c.cityNames from City c where c.stateId =:id")
	public List<Object[]> fetchCityIdAndName(Integer id);

}
