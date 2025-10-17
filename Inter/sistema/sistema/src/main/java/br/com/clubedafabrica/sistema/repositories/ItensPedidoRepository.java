package br.com.clubedafabrica.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clubedafabrica.sistema.entities.ItensPedidoEntity;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedidoEntity, Long>{

}
