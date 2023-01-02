package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITY_MASTER")
public class City {
	
	@Id
	private Integer cityId;
	
	private Integer stateId;
	
	private String cityNames;

}
