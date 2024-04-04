package es.localhost.anunciaya.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.localhost.anunciaya.models.AnuncioModel;
import es.localhost.anunciaya.models.CategoryModel;
import es.localhost.anunciaya.repositories.IAnuncioService;
import es.localhost.anunciaya.repositories.ICategoryRepository;

@Service
public class AnuncioService {
	@Autowired
	IAnuncioService anuncioservice; 
	
	public ArrayList<AnuncioModel> getAnuncios(){
		return (ArrayList<AnuncioModel>) anuncioservice.findAll(); 
	}
	
	public AnuncioModel saveAnuncio(AnuncioModel anuncio) {
		return anuncioservice.save(anuncio);
	}
	
	public Optional<AnuncioModel>getById(Long id){
		return anuncioservice.findById(id);
	}
	
	public AnuncioModel updateById(AnuncioModel request, Long id) {
		AnuncioModel anuncio = anuncioservice.findById(id).get();
		anuncio.setUsuarioAnuncio(request.getUsuarioAnuncio());
		anuncio.setCategoriaAnuncio(request.getCategoriaAnuncio());
		anuncio.setTitulo(request.getTitulo());
		anuncio.setDescripcion(request.getDescripcion());
		anuncio.setEstado(request.getEstado());
		anuncio.setDescripcion(request.getDescripcion());
		anuncio.setDivisa(request.getDivisa());
		anuncio.setPrecio(request.getPrecio());
		anuncio.setFecha_publicacion(request.getFecha_publicacion());
		anuncio.setFoto(request.getFoto());
		anuncioservice.save(anuncio);
		return anuncio;
	}
	
	public Boolean deleteAnuncio(Long id) {
		try {
			anuncioservice.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}	
