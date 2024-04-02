package com.localhost.app.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.localhost.app.models.UserModel;
import com.localhost.app.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Obtain all users
	@GetMapping
	public ArrayList<UserModel> getUsers(){
		return this.userService.getUsers();
	}
	
	//Create a new user
	@PostMapping
	public UserModel saveUser(@RequestBody UserModel user) {
		return this.userService.saveUser(user);
	}
	//Obtain user for id
	@GetMapping(path="/{id}")
	public Optional<UserModel> getUserById(@PathVariable Long id){
		return this.userService.getById(id);	
	}
	
	@PutMapping(path="/{id}")
	public UserModel updateById(@RequestBody UserModel request,@PathVariable Long id ) {
		return this.userService.updateById(request, id);
		}
	
	@DeleteMapping(path="/{id}")	
	public String deleteById(@PathVariable("id") Long id) {
		boolean ok= this.userService.deleteUser(id);
		
		if(ok) {
			return "User with id "+id+" deleted";
		}
		else {
			return "Error, it's not possible to deleted user with id "+id;
		}
	}
}
