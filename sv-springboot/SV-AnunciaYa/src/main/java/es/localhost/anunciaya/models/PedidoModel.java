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
@Table(name = "pedido")
public class PedidoModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne 
    @JoinColumn(name = "id_comprador",nullable = false)
    private UserModel usuarioComprador; 
	
	@ManyToOne 
    @JoinColumn(name = "id_anuncio",nullable = false)
    private CategoryModel anuncioCompra; 
	
	@Column(name = "fech_pedido",nullable = false)
	private String fech_pedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserModel getUsuarioComprador() {
		return usuarioComprador;
	}

	public void setUsuarioComprador(UserModel usuarioComprador) {
		this.usuarioComprador = usuarioComprador;
	}

	public CategoryModel getAnuncioCompra() {
		return anuncioCompra;
	}

	public void setAnuncioCompra(CategoryModel anuncioCompra) {
		this.anuncioCompra = anuncioCompra;
	}

	public String getFech_pedido() {
		return fech_pedido;
	}

	public void setFech_pedido(String fech_pedido) {
		this.fech_pedido = fech_pedido;
	}
	
	
}
