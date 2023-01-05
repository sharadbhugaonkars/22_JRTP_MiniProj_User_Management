package com.example.demo.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EmailUtils;
import com.example.demo.binding.LoginForm;
import com.example.demo.binding.UnLockAccountForm;
import com.example.demo.binding.UserForm;
import com.example.demo.entity.City;
import com.example.demo.entity.Country;
import com.example.demo.entity.State;
import com.example.demo.entity.User;
import com.example.demo.repo.CityRepository;
import com.example.demo.repo.CountryRepository;
import com.example.demo.repo.StateRepository;
import com.example.demo.repo.UserRepository;
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
	private EmailUtils emailUtils;

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
	public String registerUser(UserForm userform) {

		// copy data from binding obj to entity obj
		User entity = new User();
		BeanUtils.copyProperties(userform, entity);

		// Generate & set Random Password
		entity.setPassword(generateRandomPwd());
		
		//set Account Status as LOCKED
		entity.setAccStatus("LOCKED");

		userRepository.save(entity);
		
		// TODO send email to Unlock account
		
		String to = userform.getEmail();
		String subject ="Registration Email";
		String body =readEmailBody("REG_EMAIL_BODY.txt",entity);
		emailUtils.sendEmail(to, subject, body);
				
		return "User Account Created";
	}

	@Override
	public String checkEmail(String email) {

		User user = userRepository.findByEmail(email);

		if (user == null) {

			return "Unique Email Id:";

		}

		return "Duplicate Email Id:";

	}

	@Override
	public String unlockAccount(UnLockAccountForm unlockAccForm) {

		String email = unlockAccForm.getEmail();
		User user = userRepository.findByEmail(email);

		if (user.getPassword().equals(unlockAccForm.getTempPwd())) {
			user.setPassword(unlockAccForm.getNewPwd());
			user.setAccStatus("UNLOCKED");
			userRepository.save(user);
			return "Account UnLocked";
		}
		return "Invalid Temporiry Password ";
	}

	@Override
	public String login(LoginForm loginForm) {

		User user = userRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPwd());
		if (user == null) {
			return "Invalid Credentials";
		}
		if (user.getAccStatus().equals("LOCKED")) {

			return "Account locked";
		}
		return "Login Success";
	}

	@Override
	public String forgotPwd(String email) {

		User user = userRepository.findByEmail(email);
		if(user == null) {
			return "No Account Found";
		}
		
		//TODO :Send email to user with password
		String subject ="Recover Password";
		String body = readEmailBody("FORGOT_PWD_EMAIL_BODY.txt",user);
		
		emailUtils.sendEmail(email, subject, body);
		
		return "Password sent to registered email";
	}

	public String insertcountry(Country country) {
		
		countryRepository.save(country);
		
		return "Country Created";
				}
	
public String insertstate(State state) {
		
		stateRepository.save(state);
		
		return "State Created";
				}

public String insertcity(City city) {
	
	cityRepository.save(city);
	
	return "City Created";
			}

	private String generateRandomPwd() {
		
		String text ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb =new StringBuilder();
		Random random = new Random();
			int pwdLength = 6;
		for(int i = 1;i <=pwdLength ; i++) {
			int index = random.nextInt(text.length());
			sb.append(text.charAt(index));
		}
		return sb.toString();
	}
	
	public String readEmailBody(String filename,User user) {
		
		StringBuffer sb = new StringBuffer();
		try(Stream<String> lines = Files.lines(Paths.get(filename))){
			
			lines.forEach(line -> {
				
				
				line = line.replace("${FNAME}",user.getFName());
				line = line.replace("${LNAME}",user.getLName());
				line = line.replace("${Temp_PWD}",user.getPassword());
				line = line.replace("${EMAIL}",user.getEmail());
				line = line.replace("${PWD}",user.getPassword());
				sb.append(line);
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
	

