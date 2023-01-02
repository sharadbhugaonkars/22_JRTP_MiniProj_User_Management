package com.example.demo.service;


import java.util.Map;

import com.example.demo.entity.LoginForm;
import com.example.demo.entity.UnLockAccForm;
import com.example.demo.entity.User;

public interface IUserService {
	
	public String checkEmail(User user);
	
	public Map<Integer,String> fetchCountryIdAndName();
	
	public Map<Integer,String> fetchStateIdAndName(Integer id);
	
	public Map<Integer,String> fetchCityIdAndName(Integer id);
	
	public String registerUser(User user);
	
	public String unlockAccount(UnLockAccForm accForm,User user);
	
	public String login(LoginForm loginForm);
	
	public String forgotPwd(String email,User user);
	
}
