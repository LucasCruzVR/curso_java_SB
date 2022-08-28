package com.projetoaula.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoaula.domain.Categoria;
import com.projetoaula.domain.Cliente;
import com.projetoaula.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
	
}
