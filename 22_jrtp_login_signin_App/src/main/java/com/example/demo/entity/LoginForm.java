package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="LOGINFORM_TAB")
public class LoginForm {
	
	@Id
	private Integer loginId; 
	
	private String email;
	
	private String pwd;

}
