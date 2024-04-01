package es.localhost.anunciaya.controllers;

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

import es.localhost.anunciaya.models.UserModel;
import es.localhost.anunciaya.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
		@Autowired
		private UserService usuarioService;
	
		
		@GetMapping
		public ArrayList<UserModel> getUsers(){
			return this.usuarioService.getUsers();
		}
		
		//Create a new user
		@PostMapping
		public UserModel saveUser(@RequestBody UserModel user) {
			return this.usuarioService.saveUser(user);
		} 
		
		@GetMapping(path="/{id}")
		public Optional<UserModel> getUserById(@PathVariable Long id){
			return this.usuarioService.getById(id);
		}
		
		@PutMapping(path="/{id}")
		public UserModel updateById(@RequestBody UserModel request,@PathVariable Long id ) {
			return this.usuarioService.updateById(request, id);
			}
		
		@DeleteMapping(path="/{id}")	
		public String deleteById(@PathVariable("id") Long id) {
			boolean ok = this.usuarioService.deleteUser(id);
			
			if(ok) {
				return "Usuario con "+ id +" ha sido borrado";
			}
			else {
				return "Error, no es posible borrar el usuario con id "+id;
			}
		}
}
