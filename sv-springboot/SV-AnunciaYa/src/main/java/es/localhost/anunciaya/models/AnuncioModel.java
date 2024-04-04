package es.localhost.anunciaya.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="anuncio")
public class AnuncioModel {

	@ManyToOne 
    @JoinColumn(name = "id_usuario",nullable = false)
    private UserModel usuarioAnuncio; 
	
	@ManyToOne 
    @JoinColumn(name = "id_categoria",nullable = false)
    private CategoryModel categoriaAnuncio; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo",nullable = false)
	private String titulo;
	
	@Column(name = "descripcion",nullable = false)
	private String descripcion;
	
	@Column(name = "estado",nullable = false)
	private String estado;
	
	@Column(name = "ubicacion",nullable = false)
	private String ubicacion;
	
	@Column(name = "precio",nullable = false)
	private Float precio;
	
	@Column(name="divisa",nullable = false)
	private String divisa;
	
	@Column(name="fech_public",nullable = false)
	private String fecha_publicacion;
	
	@Column(name="foto",nullable = false)
	private String foto;

	
	public UserModel getUsuarioAnuncio() {
		return usuarioAnuncio;
	}

	public void setUsuarioAnuncio(UserModel id_usuario) {
		this.usuarioAnuncio = id_usuario;
	}

	public CategoryModel getCategoriaAnuncio() {
		return categoriaAnuncio;
	}

	public void setCategoriaAnuncio(CategoryModel categoriaAnuncio) {
		this.categoriaAnuncio = categoriaAnuncio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public String getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(String fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
}
