package es.localhost.anunciaya.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.localhost.anunciaya.models.UserModel;
import es.localhost.anunciaya.repositories.IUserRepository;


@Service
public class UserService {
	
	@Autowired
	IUserRepository userRepository; // Dependecias inyectadas
	
	public ArrayList<UserModel> getUsers(){
		return (ArrayList<UserModel>) userRepository.findAll(); // Método de devuelve todos los usuarios
	}
	
	public UserModel saveUsers(UserModel user) {
		return userRepository.save(user);
	}
	
	public Optional<UserModel> getById(Long id){
		return userRepository.findById(id);
	}
	
	public UserModel updateById(UserModel request, Long id) {
		UserModel user = userRepository.findById(id).get();
		user.setNombre(request.getNombre());;
		user.setApellidos(request.getApellidos());
		user.setNomb_usu(request.getNomb_usu());
		user.setContras(request.getContras());
		user.setEmail(request.getEmail());
		user.setFechaNacimiento(request.getFechaNacimiento());
		user.setTelefono(request.getTelefono());
		user.setFoto(request.getFoto());
		userRepository.save(user);
		return user;
	}
	
	public Boolean deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
