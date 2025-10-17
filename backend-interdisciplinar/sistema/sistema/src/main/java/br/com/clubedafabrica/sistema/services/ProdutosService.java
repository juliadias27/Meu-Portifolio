package br.com.clubedafabrica.sistema.services;

import br.com.clubedafabrica.sistema.entities.ProdutosEntity;
import br.com.clubedafabrica.sistema.repositories.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepository produtosRepository;

    public List<ProdutosEntity> listarTodos() {
        return produtosRepository.findAll();
    }

    public Optional<ProdutosEntity> buscarPorId(Long id) {
        return produtosRepository.findById(id);
    }

    public ProdutosEntity incluir(ProdutosEntity produto) {
        return produtosRepository.save(produto);
    }

    public ProdutosEntity editar(Long id, ProdutosEntity dadosNovos) {
        Optional<ProdutosEntity> optionalProduto = produtosRepository.findById(id);

        if (optionalProduto.isEmpty()) {
            return null;
        }

        ProdutosEntity produtoExistente = optionalProduto.get();
        produtoExistente.setDescricao(dadosNovos.getDescricao());
        produtoExistente.setPreco(dadosNovos.getPreco());
        produtoExistente.setQuantEstoque(dadosNovos.getQuantEstoque());
        produtoExistente.setStatus(dadosNovos.getStatus());
        produtoExistente.setCategoria(dadosNovos.getCategoria());

        return produtosRepository.save(produtoExistente);
    }

    public boolean excluir(Long id) {
        if (produtosRepository.existsById(id)) {
            produtosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}