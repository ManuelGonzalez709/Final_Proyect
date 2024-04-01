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
	IUserRepository usuarioRepository;
	
	public ArrayList<UserModel> getUsers(){
		return (ArrayList<UserModel>) usuarioRepository.findAll(); // Método de devuelve todos los usuarios
	}
	
	public UserModel saveUsers(UserModel user) {
		return usuarioRepository.save(user);
	}
	
	public Optional<UserModel> getById(Long id){
		return usuarioRepository.findById(id);
	}
	
	public UserModel updateById(UserModel request, Long id) {
		UserModel user = usuarioRepository.findById(id).get();
		user.setNombre(request.getNombre());;
		user.setApellidos(request.getApellidos());
		user.setFechNac(request.getFechNac());
		user.setEmail(request.getEmail());
		user.setNombUsu(request.getNombUsu());
		user.setTelefono(request.getTelefono());
		user.setContras(request.getContras());
		user.setFoto(request.getFoto());
		usuarioRepository.save(user);
		return user;
	}
	
	public Boolean deleteUser(Long id) {
		try {
			usuarioRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	
}
