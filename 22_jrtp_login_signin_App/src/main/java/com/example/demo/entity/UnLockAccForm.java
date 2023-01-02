package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="UNLOCKACC_TAB")
public class UnLockAccForm {
	
	@Id
	private Integer unlockAccId;
	
	private String email;
	
	private String tempPwd;
	
	private String newPwd;
	
	private String confirmPwd;

}
