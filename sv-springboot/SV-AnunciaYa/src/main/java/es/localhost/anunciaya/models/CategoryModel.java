package es.localhost.anunciaya.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categoria")

public class CategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	@Column(name = "descripcion",nullable = false)
	private String descripcion;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public void setDescripcion(String a) {descripcion = a; }
	public String getDescripcion() {return descripcion;}
	
}
