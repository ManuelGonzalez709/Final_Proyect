package es.localhost.anunciaya.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="direccion_envio")
public class DirEnvModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "direccion",nullable = false)
	private String direccion;
	
	@Column(name = "cp",nullable = false)
	private String cp;
	
	@Column(name = "poblacion",nullable = false)
	private String poblacion;
	
	@Column(name = "provincia",nullable = false)
	private String provincia;
	
	@Column(name = "pais",nullable = false)
	private String pais;
	
	@ManyToOne // entidad pertenece a una usuario
    @JoinColumn(name = "id_usuario",nullable = false) // almacena la clave foránea en la tabla person
	private UserModel user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvicia() {
		return provincia;
	}

	public void setProvicia(String provicia) {
		this.provincia = provicia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
}