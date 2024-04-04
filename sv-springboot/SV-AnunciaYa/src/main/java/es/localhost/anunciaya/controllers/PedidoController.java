package es.localhost.anunciaya.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.localhost.anunciaya.models.PedidoModel;
import es.localhost.anunciaya.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ArrayList<PedidoModel> getPedidos(){
		return this.pedidoService.getPedidos();
	}
	
	
	@PostMapping
	public PedidoModel saveAnuncio(@RequestBody PedidoModel pedido) {
		return this.pedidoService.savePedido(pedido);
	}
	
	
	@GetMapping(path="/{id}")
	public PedidoModel updateById(@RequestBody PedidoModel pedido, @PathVariable Long id) {
		return this.pedidoService.updateById(pedido, id);
	}
	
	
	@DeleteMapping(path="/{id}")
	public String deleteById(@PathVariable("id") Long id) {
		if(this.pedidoService.deletePedido(id)) {
			return "Category with id " + id + " deleted";
		} else {
			return "Error, it's not possible to delete category with id " + id;
		}	
	}
}
