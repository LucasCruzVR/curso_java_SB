package com.projetoaula.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projetoaula.domain.Categoria;
import com.projetoaula.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	// para sql nativo usar (nativy query = true)
	@Transactional
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat where obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

	/*
	 * EXEMPLO
	 * 
	 * @Query(value = "select distinct on (bf.type_fee) * " +
	 * "from dbo.basket_fee bf " + "where bf.type_fee = :type or (:type is null) " +
	 * "order by bf.type_fee , bf.created_date desc", nativeQuery = true)
	 * Page<BasketFee> findAll(@Param("type") FeeType type, Pageable pageable);
	 */
}
