package br.com.clubedafabrica.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clubedafabrica.sistema.entities.ProdutosEntity;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosEntity, Long>{

}
