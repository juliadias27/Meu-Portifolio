package br.com.clubedafabrica.sistema.services;

import br.com.clubedafabrica.sistema.entities.CategoriasEntity;
import br.com.clubedafabrica.sistema.repositories.CategoriasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriasService {

    private final CategoriasRepository categoriasRepository;

    public List<CategoriasEntity> listarTodas() {
        return categoriasRepository.findAll();
    }

    public Optional<CategoriasEntity> buscarPorId(Long id) {
        return categoriasRepository.findById(id);
    }

    public CategoriasEntity incluir(CategoriasEntity categoria) {
        return categoriasRepository.save(categoria);
    }

    public CategoriasEntity editar(Long id, CategoriasEntity dadosNovos) {
        Optional<CategoriasEntity> optionalCategoria = categoriasRepository.findById(id);

        if (optionalCategoria.isEmpty()) {
            return null;
        }

        CategoriasEntity categoriaExistente = optionalCategoria.get();
        categoriaExistente.setNome(dadosNovos.getNome());
        categoriaExistente.setDescricao(dadosNovos.getDescricao());

        return categoriasRepository.save(categoriaExistente);
    }

    public boolean excluir(Long id) {
        if (categoriasRepository.existsById(id)) {
            categoriasRepository.deleteById(id);
            return true;
        }
        return false;
    }
}