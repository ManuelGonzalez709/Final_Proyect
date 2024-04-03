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

import es.localhost.anunciaya.models.AnuncioModel;
import es.localhost.anunciaya.models.CategoryModel;
import es.localhost.anunciaya.services.AnuncioService;
import es.localhost.anunciaya.services.CategoryService;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {
	@Autowired
	private AnuncioService anuncioservice;
	
	@GetMapping
	public ArrayList<AnuncioModel> getAnuncios(){
		return this.anuncioservice.getAnuncios();
	}
	
	// guardar categorias
	@PostMapping
	public AnuncioModel saveAnuncio(@RequestBody AnuncioModel anuncio) {
		return this.anuncioservice.saveAnuncio(anuncio);
	}
	
	// actualiza las categorias por id
	@GetMapping(path="/{id}")
	public AnuncioModel updateById(@RequestBody AnuncioModel anuncio, @PathVariable Long id) {
		return this.anuncioservice.updateById(anuncio, id);
	}
	
	// Elimina las categorias por id
	@DeleteMapping(path="/{id}")
	public String deleteById(@PathVariable("id") Long id) {
		if(this.anuncioservice.deleteAnuncio(id)) {
			return "Category with id " + id + " deleted";
		} else {
			return "Error, it's not possible to delete category with id " + id;
		}	
	}
}
