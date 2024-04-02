package es.localhost.anunciaya.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.localhost.anunciaya.models.UserModel;
import es.localhost.anunciaya.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Obtiene todos los usuarios
	@GetMapping
	public ArrayList<UserModel> getUsers(){
		return this.userService.getUsers();
	}
	
	// Crear un nuevo usuario
	@PostMapping
	public UserModel saveUsers(@RequestBody UserModel user) {
		return this.userService.saveUsers(user);
	}
	
	// actualiza los usuarios por id
	@GetMapping(path="/{id}")
	public UserModel updateById(@RequestBody UserModel request, @PathVariable Long id) {
		return this.userService.updateById(request, id);
	}
	
	// Elimina los usuarios por id
	@DeleteMapping(path="/{id}")
	public String deleteById(@PathVariable("id") Long id) {
		boolean ok = this.userService.deleteUser(id);
		if(ok) {
			return "User with id " + id + " deleted";
		} else {
			return "Error, it's not possible to deleted user with id " + id;
		}	
	}
}













