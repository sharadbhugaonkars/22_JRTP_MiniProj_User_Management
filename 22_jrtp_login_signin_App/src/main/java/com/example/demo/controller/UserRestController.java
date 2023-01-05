package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.LoginForm;
import com.example.demo.binding.UnLockAccountForm;
import com.example.demo.binding.UserForm;
import com.example.demo.entity.City;
import com.example.demo.entity.Country;
import com.example.demo.entity.State;
import com.example.demo.service.IUserService;

@RestController
public class UserRestController {

	@Autowired
	private IUserService service;

	@GetMapping("/checkuser/{email}")
	public ResponseEntity<String> authenticateUser(@PathVariable String email) {

		String s = service.checkEmail(email);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping("/country")
	public ResponseEntity<Map<Integer, String>> fetchCountryIdAndName() {

		Map<Integer, String> countries = service.fetchCountryIdAndName();

		return ResponseEntity.ok(countries);
	}

	@GetMapping("/state/{countryId}")
	public ResponseEntity<Map<Integer, String>> fetchStateIdAndName(@PathVariable Integer countryId) {

		Map<Integer, String> states = service.fetchStateIdAndName(countryId);

		return ResponseEntity.ok(states);
	}

	@GetMapping("/city/{stateId}")
	public ResponseEntity<Map<Integer, String>> fetchCityIdAndName(@PathVariable Integer stateId) {

		Map<Integer, String> cities = service.fetchCityIdAndName(stateId);

		return ResponseEntity.ok(cities);
	}

	@PostMapping("/registeruser")
	public ResponseEntity<String> registerUser(@RequestBody UserForm userform) {

		String status= service.registerUser(userform);

		return new ResponseEntity<>(status, HttpStatus.CREATED);

	}

	@PostMapping("/unlock")
	public ResponseEntity<String> unlockAccount(@RequestBody UnLockAccountForm unlockAccForm) {
		

		String status = service.unlockAccount(unlockAccForm);
		
		return new ResponseEntity<>(status, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<String>  login(@RequestBody LoginForm loginForm) {
		
		String status =	service.login(loginForm);
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	@GetMapping("/forgotpwd/{email}")
	public ResponseEntity<String>  forgotPwd(@PathVariable String email){
		
            String status =	service.forgotPwd(email);
		
		     return new ResponseEntity<>(status, HttpStatus.OK);
		
	}
	@PostMapping("/insertcountry")
	public ResponseEntity<String> insertcountry(@RequestBody Country country) {

		String status= service.insertcountry(country);

		return new ResponseEntity<>(status, HttpStatus.CREATED);

	}
	
	@PostMapping("/insertstate")
	public ResponseEntity<String> insertcountry(@RequestBody State state) {

		String status= service.insertstate(state);

		return new ResponseEntity<>(status, HttpStatus.CREATED);

	}
	
	@PostMapping("/insertcity")
	public ResponseEntity<String> insertcountry(@RequestBody City city) {

		String status= service.insertcity(city);

		return new ResponseEntity<>(status, HttpStatus.CREATED);

	}
}










