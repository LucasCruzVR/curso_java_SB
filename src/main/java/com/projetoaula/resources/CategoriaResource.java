package com.projetoaula.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetoaula.domain.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1, "informática");
		Categoria cat2 = new Categoria(2, "engenharia");
		List<Categoria> listCategorias = new ArrayList<>();
		listCategorias.add(cat1);
		listCategorias.add(cat2);
		return listCategorias;
	}
}
