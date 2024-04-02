package es.localhost.anunciaya.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre")
	private String nombre;  
	
	@Column(name="apellidos")
	private String apellidos; 
	
	@Column(name="nomb_usu")
	private String nombUsu; 
	
	@Column(name="contras")
	private String contras; 
	
	@Column(name="fecha_nacimiento")
	private Date fechNac; 
	
	@Column(name="email")
	private String email; 
	
	@Column(name="telefono")
	private String telefono; 
	
	@Column(name="foto")
	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombUsu() {
		return nombUsu;
	}

	public void setNombUsu(String nombUsu) {
		this.nombUsu = nombUsu;
	}

	public String getContras() {
		return contras;
	}

	public void setContras(String contras) {
		this.contras = contras;
	}

	public Date getFechNac() {
		return fechNac;
	}

	public void setFechNac(Date fechNac) {
		this.fechNac = fechNac;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
