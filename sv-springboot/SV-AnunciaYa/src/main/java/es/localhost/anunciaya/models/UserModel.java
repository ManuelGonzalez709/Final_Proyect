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
	
	@Column(name = "nombre",nullable = false)
	private String nombre;
	
	@Column(name = "apellidos",nullable = false)
	private String apellidos;
	
	@Column(name = "nomb_usu",nullable = false)
	private String nomb_usu;
	
	@Column(name = "contras",nullable = false)
	private String contras;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name="fecha_nacimiento",nullable = false)
	private String fechaNacimiento;
	
	@Column(name="telefono",nullable = false)
	private String telefono;
	
	@Column(name="foto",nullable = false)
	private String foto;
	
	public UserModel() {
    }

    public UserModel(Long id, String nombre, String apellidos, String nomb_usu, String contras, String email, String fechaNacimiento, String telefono, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nomb_usu = nomb_usu;
        this.contras = contras;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.foto = foto;
    }
	
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

	public String getNomb_usu() {
		return nomb_usu;
	}

	public void setNomb_usu(String nomb_usu) {
		this.nomb_usu = nomb_usu;
	}

	public String getContras() {
		return contras;
	}

	public void setContras(String contras) {
		this.contras = contras;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
