package es.localhost.anunciaya.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.localhost.anunciaya.models.DirEnvModel;
import es.localhost.anunciaya.repositories.IDirEnvRepository;

@Service
public class DirEnvService {
	@Autowired
    private IDirEnvRepository dirEnvRepository; 
	
	// Método que devuelve todas las direcciones de envio
    public ArrayList<DirEnvModel> getDirEnv() {
        return (ArrayList<DirEnvModel>) dirEnvRepository.findAll();
    }
    
    // Método para guardar una nueva direccion de envio
    public DirEnvModel saveDirEnv(DirEnvModel dirEnv) {
        return dirEnvRepository.save(dirEnv);
    }
	
	// Método para obtener una direccion envio por su id
    public Optional<DirEnvModel> getById(Long id) {
        return dirEnvRepository.findById(id);
    }
    
    // Método para actualizar una direccion envio por su id
    public DirEnvModel updateById(DirEnvModel request, Long id) {
        Optional<DirEnvModel> optionalDirEnv = dirEnvRepository.findById(id);
        if (optionalDirEnv.isPresent()) {
            DirEnvModel dirEnv = optionalDirEnv.get();
            dirEnv.setDireccion(request.getDireccion());
            dirEnv.setCp(request.getCp());
            dirEnv.setPoblacion(request.getPoblacion());
            dirEnv.setProvicia(request.getProvicia());
            dirEnv.setPais(request.getPais());
            return dirEnvRepository.save(dirEnv);
        } else {
            return null; // Manejo de caso en que la direccion envio no exista
        }
    }
    
    // Método para eliminar una direccion envio por su id
    public boolean deleteDirEnv(Long id) {
        try {
        	dirEnvRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}