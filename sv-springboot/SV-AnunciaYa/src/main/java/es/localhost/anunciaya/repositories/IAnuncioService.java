package es.localhost.anunciaya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.localhost.anunciaya.models.AnuncioModel;
import es.localhost.anunciaya.models.CategoryModel;

public interface IAnuncioService extends JpaRepository <AnuncioModel,Long>{

}
