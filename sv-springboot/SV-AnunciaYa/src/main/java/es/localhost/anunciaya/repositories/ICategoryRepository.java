package es.localhost.anunciaya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.localhost.anunciaya.models.CategoryModel;

public interface ICategoryRepository extends JpaRepository <CategoryModel,Long>{

}
