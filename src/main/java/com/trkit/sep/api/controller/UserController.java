package com.trkit.sep.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trkit.sep.api.entity.User;
import com.trkit.sep.api.model.LoginRequest;
import com.trkit.sep.api.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/login-user")
	public User loginUser(@RequestBody LoginRequest request) {

		return service.loginUser(request);
	}

	@CrossOrigin(methods = RequestMethod.POST)
	@PostMapping("/register-user")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		User registerUser = service.registerUser(user);
		if (registerUser != null) {
			return new ResponseEntity<String>("Registered", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("UnRegistered", HttpStatus.OK);
		}
	}

	@GetMapping("/get-user-by-username/{username}")
	public User getUserById(@PathVariable String username) {
		return service.getUserByName(username);

	}

	@GetMapping("/get-all-user")
	public List<User> getAllUser() {
		return service.getAllUser();

	}

	@GetMapping("/get-all-admin")
	public List<User> getAllAdmins() {
		return service.getAllAdmins();
	}

	@GetMapping("/get-all-faculty")
	public List<User> getAllFaculties() {
		return service.getAllFaculties();
	}

	@DeleteMapping("/delete-user-by-username")
	public String deleteUserById(@RequestParam String username) {
		return service.deleteUserById(username);

	}

	@PutMapping("/update-user")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);

	}

}
