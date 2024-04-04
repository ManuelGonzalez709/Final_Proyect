package es.localhost.anunciaya.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.localhost.anunciaya.models.PedidoModel;
import es.localhost.anunciaya.models.UserModel;
import es.localhost.anunciaya.repositories.IPedidoRepository;
import es.localhost.anunciaya.repositories.IUserRepository;

@Service
public class PedidoService {
	@Autowired
	IPedidoRepository pedidoRepository; 
	
	public ArrayList<PedidoModel> getPedidos(){
		return (ArrayList<PedidoModel>) pedidoRepository.findAll(); 
	}
	
	public PedidoModel savePedido(PedidoModel pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Optional<PedidoModel> getById(Long id){
		return pedidoRepository.findById(id);
	}
	
	public PedidoModel updateById(PedidoModel request, Long id) {
		PedidoModel pedido = pedidoRepository.findById(id).get();
		pedido.setUsuarioComprador(request.getUsuarioComprador());
		pedido.setAnuncioCompra(request.getAnuncioCompra());
		pedido.setFech_pedido(request.getFech_pedido());
		pedidoRepository.save(pedido);
		return pedido;
	}
	
	public Boolean deletePedido(Long id) {
		try {
			pedidoRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
