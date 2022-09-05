package com.projetoaula.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projetoaula.domain.Categoria;
import com.projetoaula.domain.Produto;
import com.projetoaula.repositories.CategoriaRepository;
import com.projetoaula.repositories.ProdutoRepository;
import com.projetoaula.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> {
			throw new ObjectNotFoundException("Produto não encontrada! ID: " + id);
		});
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
	}
}
