package com.localhost.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.localhost.app.models.UserModel;
import com.localhost.app.repositories.IUserRepository;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	IUserRepository userRepository; //Dependencies inyection
	
	public ArrayList<UserModel> getUsers(){
		return (ArrayList<UserModel>) userRepository.findAll(); //Methods that return all users
	}
	
	public UserModel saveUser(UserModel user) {
		return userRepository.save(user);
	}
	
	public Optional<UserModel> getById(Long id){
		return userRepository.findById(id);
	}
	
	public UserModel updateById(UserModel request,Long id){
		UserModel user = userRepository.findById(id).get();
		user.setNombre(request.getNombre());;
		user.setApellidos(request.getApellidos());
		user.setFechNac(request.getFechNac());
		user.setEmail(request.getEmail());
		user.setNombUsu(request.getNombUsu());
		user.setTelefono(request.getTelefono());
		user.setContras(request.getContras());
		user.setFoto(request.getFoto());
		userRepository.save(user);
		return user;
	}
	

	public Boolean deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
