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

import es.localhost.anunciaya.models.CategoryModel;
import es.localhost.anunciaya.models.UserModel;
import es.localhost.anunciaya.services.CategoryService;
import es.localhost.anunciaya.services.UserService;

@RestController
@RequestMapping("/categoria")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	//obtener categorias
	@GetMapping
	public ArrayList<CategoryModel> getCategorys(){
		return this.categoryService.getCategorys();
	}
	
	// guardar categorias
	@PostMapping
	public CategoryModel saveCategory(@RequestBody CategoryModel category) {
		return this.categoryService.saveCategory(category);
	}
	
	// actualiza las categorias por id
	@GetMapping(path="/{id}")
	public CategoryModel updateById(@RequestBody CategoryModel request, @PathVariable Long id) {
		return this.categoryService.updateById(request, id);
	}
	
	// Elimina las categorias por id
	@DeleteMapping(path="/{id}")
	public String deleteById(@PathVariable("id") Long id) {
		boolean ok = this.categoryService.deleteCategory(id);
		if(ok) {
			return "Category with id " + id + " deleted";
		} else {
			return "Error, it's not possible to delete category with id " + id;
		}	
	}
}
