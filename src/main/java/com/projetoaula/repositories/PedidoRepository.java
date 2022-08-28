package com.projetoaula.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoaula.domain.Categoria;
import com.projetoaula.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
}
