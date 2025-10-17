package br.com.clubedafabrica.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.clubedafabrica.sistema.entities.UsuariosEntity;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long>{
    
}
