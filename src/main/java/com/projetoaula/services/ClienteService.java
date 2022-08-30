package com.projetoaula.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projetoaula.domain.Cliente;
import com.projetoaula.dto.ClienteDTO;
import com.projetoaula.repositories.ClienteRepository;
import com.projetoaula.services.exceptions.DataIntegrityException;
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

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException err) {
			throw new DataIntegrityException("Não é possível excluir uma Cliente que tenha Pedidos vinculados.");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> pageClientes(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
	
	private void updateData(Cliente novoCliente, Cliente clienteParams) {
		novoCliente.setNome(clienteParams.getNome());
		novoCliente.setEmail(clienteParams.getEmail());
	}
}
