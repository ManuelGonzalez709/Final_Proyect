package es.localhost.anunciaya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.localhost.anunciaya.models.PedidoModel;

public interface IPedidoRepository extends JpaRepository <PedidoModel, Long>{

}
