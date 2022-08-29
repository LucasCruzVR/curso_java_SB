package com.projetoaula.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoaula.domain.Categoria;
import com.projetoaula.domain.Cliente;
import com.projetoaula.repositories.CategoriaRepository;
import com.projetoaula.repositories.ClienteRepository;
import com.projetoaula.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> {
			throw new ObjectNotFoundException("Cliente não encontrada! ID: " + id);
		});
	}
}
