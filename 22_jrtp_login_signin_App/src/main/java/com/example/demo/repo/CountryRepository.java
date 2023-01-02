package com.example.demo.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Country;




public interface CountryRepository extends JpaRepository<Country,Integer> {
	
	@Query("Select c.countryId,c.countryNames from Country c")
	public  List<Object[]> fetchCountryIdAndName();
}
