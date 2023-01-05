package com.example.demo.service;


import java.util.Map;

import com.example.demo.binding.LoginForm;
import com.example.demo.binding.UnLockAccountForm;
import com.example.demo.binding.UserForm;
import com.example.demo.entity.City;
import com.example.demo.entity.Country;
import com.example.demo.entity.State;


public interface IUserService {
	
	public String checkEmail(String email);
	
	public Map<Integer,String> fetchCountryIdAndName();
	
	public Map<Integer,String> fetchStateIdAndName(Integer id);
	
	public Map<Integer,String> fetchCityIdAndName(Integer id);
	
	public String registerUser(UserForm userform);
	
	public String unlockAccount(UnLockAccountForm unlockAccForm);
	
	public String login(LoginForm loginForm);
	
	public String forgotPwd(String email);
	
	public String insertcountry(Country country);
	
	public String insertstate(State state);
	
	public String insertcity(City city);
	
}
