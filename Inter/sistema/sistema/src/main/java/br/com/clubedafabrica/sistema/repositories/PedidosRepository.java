package br.com.clubedafabrica.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.clubedafabrica.sistema.entities.PedidosEntity;

@Repository
public interface PedidosRepository extends JpaRepository<PedidosEntity, Long> {
    
}
