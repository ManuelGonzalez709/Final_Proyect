package es.localhost.anunciaya.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.localhost.anunciaya.models.CategoryModel;
import es.localhost.anunciaya.models.UserModel;
import es.localhost.anunciaya.repositories.ICategoryRepository;
import es.localhost.anunciaya.repositories.IUserRepository;

@Service
public class CategoryService {
	@Autowired
	ICategoryRepository categoryRepository; 
	
	public ArrayList<CategoryModel> getCategorys(){
		return (ArrayList<CategoryModel>) categoryRepository.findAll(); 
	}
	
	public CategoryModel saveCategory(CategoryModel category) {
		return categoryRepository.save(category);
	}
	
	public Optional<CategoryModel>getById(Long id){
		return categoryRepository.findById(id);
	}
	
	public CategoryModel updateById(CategoryModel request, Long id) {
		CategoryModel category = categoryRepository.findById(id).get();
		category.setDescripcion(request.getDescripcion());
		categoryRepository.save(category);
		return category;
	}
	
	public Boolean deleteCategory(Long id) {
		try {
			categoryRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
