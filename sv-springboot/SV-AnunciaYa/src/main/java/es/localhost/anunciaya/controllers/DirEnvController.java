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
import es.localhost.anunciaya.models.DirEnvModel;
import es.localhost.anunciaya.services.DirEnvService;

@RestController
@RequestMapping("/dir_env")
public class DirEnvController {
	@Autowired
    private DirEnvService dirEnvService;
    
    // Obtiene todas las direcciones de envio
    @GetMapping
    public ArrayList<DirEnvModel> getDirEnv() {
        return dirEnvService.getDirEnv();
    }
    
    // Crea una nueva persona
    @PostMapping
    public DirEnvModel savePerson(@RequestBody DirEnvModel dirEnv) {
        return dirEnvService.saveDirEnv(dirEnv);
    }
    
    // Obtiene la persona por su id
    @GetMapping("/{id}")
    public Optional<DirEnvModel> getPersonById(@PathVariable("id") Long id) {
        return dirEnvService.getById(id);
    }
    
    // Actualiza una persona por su id
    @PutMapping("/{id}")
    public DirEnvModel updateById(@RequestBody DirEnvModel request, @PathVariable("id") Long id) {
        return dirEnvService.updateById(request, id);
    }
    
    // Elimina una persona por su id
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean deleted = dirEnvService.deleteDirEnv(id);
        if (deleted) {
            return "La direccion de envio con id " + id + " ha sido eliminada exitosamente.";
        } else {
            return "No se pudo eliminar la direccion de envio con id " + id + ". Verifique el ID proporcionado.";
        }
    }
	
	

}