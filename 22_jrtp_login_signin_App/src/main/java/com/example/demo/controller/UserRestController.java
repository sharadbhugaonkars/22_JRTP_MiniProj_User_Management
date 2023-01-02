package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoginForm;
import com.example.demo.entity.UnLockAccForm;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;

@RestController
public class UserRestController {

	@Autowired
	private IUserService service;

	@PostMapping("/checkuser")
	public ResponseEntity<String> authenticateUser(@RequestBody User user) {

		String s = service.checkEmail(user);
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
	public ResponseEntity<String> registerUser(@RequestBody User user) {

		String s = service.registerUser(user);

		return new ResponseEntity<>(s, HttpStatus.OK);

	}

	@PutMapping("/modifypwd")
	public ResponseEntity<String> updatePwd(@RequestBody UnLockAccForm accForm,User user) {
		

		String s = service.unlockAccount(accForm,user);
		
		return new ResponseEntity<>(s, HttpStatus.OK);

	}

	@PutMapping("/login")
	public ResponseEntity<String>  login(@RequestBody LoginForm login) {
		
		String s =	service.login(login);
		
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	@PutMapping("/forgotpwd")
	public ResponseEntity<String>  forgotPwd(@RequestBody String email,User user){
		
            String s =	service.forgotPwd(email,user);
		
		     return new ResponseEntity<>(s, HttpStatus.OK);
		
	}
}










