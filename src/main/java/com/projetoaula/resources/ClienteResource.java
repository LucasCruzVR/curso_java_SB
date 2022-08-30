package com.projetoaula.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetoaula.domain.Cliente;
import com.projetoaula.domain.Cliente;
import com.projetoaula.dto.ClienteDTO;
import com.projetoaula.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO obj) {
		Cliente cliente = service.fromDTO(obj);
		cliente.setId(id);
		cliente = service.update(cliente);
		return ResponseEntity.ok().body(cliente);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> clientes = service.findAll();
		List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> new ClienteDTO(cliente))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(clientesDTO);
	}
}
