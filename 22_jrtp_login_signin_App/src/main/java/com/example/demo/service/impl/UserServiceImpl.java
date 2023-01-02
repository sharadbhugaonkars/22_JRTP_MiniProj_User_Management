package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginForm;
import com.example.demo.entity.UnLockAccForm;
import com.example.demo.entity.User;
import com.example.demo.exception.UserEmailNotFoundException;
import com.example.demo.repo.CityRepository;
import com.example.demo.repo.CountryRepository;
import com.example.demo.repo.LoginRepository;
import com.example.demo.repo.StateRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.repo.unlockAccRepository;
import com.example.demo.service.IUserService;

@Service

public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private unlockAccRepository unlockAccRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public String checkEmail(User user) throws UserEmailNotFoundException {

		if (userRepository.existsByEmail(user.getEmail()) && userRepository.existsByPassword(user.getPassword())
				&& user.getAccStatus().booleanValue() == true) {

			return "WelCome To Login Page:";

		} else if (userRepository.existsByEmail(user.getEmail()) && userRepository.existsByPassword(user.getPassword())
				&& user.getAccStatus().booleanValue() == false) {

			return "Your Account is Locked";
		} else {

			return "Invalid Credentials ";
		}

	}

	@Override
	public Map<Integer, String> fetchCountryIdAndName() {

		List<Object[]> result = countryRepository.fetchCountryIdAndName();

		Map<Integer, String> map = null;
		if (result != null && !result.isEmpty()) {
			map = new HashMap<Integer, String>();
			for (Object[] object : result) {
				map.put(((Integer) object[0]), (String) object[1]);
			}
		}

		return map;
	}

	@Override
	public Map<Integer, String> fetchStateIdAndName(Integer id) {

		List<Object[]> result = stateRepository.fetchStateIdAndName(id);
		Map<Integer, String> map = null;
		if (result != null && !result.isEmpty()) {
			map = new HashMap<Integer, String>();
			for (Object[] object : result) {
				map.put(((Integer) object[0]), (String) object[1]);
			}
		}
		return map;
	}

	@Override
	public Map<Integer, String> fetchCityIdAndName(Integer id) {

		List<Object[]> result = cityRepository.fetchCityIdAndName(id);

		Map<Integer, String> map = null;
		if (result != null && !result.isEmpty()) {
			map = new HashMap<Integer, String>();
			for (Object[] object : result) {
				map.put(((Integer) object[0]), (String) object[1]);
			}
		}

		return map;
	}

	@Override
	public String registerUser(User user) {

		userRepository.save(user);
		return "User Created";
	}

	@Override
	public String unlockAccount(UnLockAccForm accForm,User user) {

		unlockAccRepository.save(accForm);
		
		if (accForm.getEmail().equals(user.getEmail())  && user.getPassword().equals(accForm.getTempPwd()) && accForm.getNewPwd().equals(accForm.getConfirmPwd())) {
			user.setPassword(accForm.getNewPwd());
			user.setAccStatus(true);
			
		return "Account UnLocked , please proceed with login";
		}else {
			return "Account Not Found ";
		}
	}

	@Override
	public String login(LoginForm loginForm) {

		loginRepository.save(loginForm);
		if (userRepository.existsByEmail(loginForm.getEmail()) && userRepository.existsByPassword(loginForm.getPwd())){
			
			return "WelCome To AshokIt";
			
		}else {
			return "Login Failed";
		}

		
	}

	@Override
	public String forgotPwd(String email,User user) {

		
		if (user.getEmail().equals(email)) {
			return ("We wil send Password to Email" + email + "Your Password is:" + user.getPassword());
		} else {
			return "Email is not Registered!!!";
		}

	}
}
