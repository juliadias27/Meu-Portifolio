package br.com.clubedafabrica.sistema.controllers;

import br.com.clubedafabrica.sistema.entities.ProdutosEntity;
import br.com.clubedafabrica.sistema.services.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutosController {

    private final ProdutosService produtosService;

    @GetMapping
    public ResponseEntity<List<ProdutosEntity>> listarTodos() {
        List<ProdutosEntity> produtos = produtosService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosEntity> buscarPorId(@PathVariable Long id) {
        Optional<ProdutosEntity> produto = produtosService.buscarPorId(id);
        return produto.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutosEntity> incluir(@RequestBody ProdutosEntity produto) {
        ProdutosEntity novoProduto = produtosService.incluir(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutosEntity> editar(@PathVariable Long id, @RequestBody ProdutosEntity dadosNovos) {
        ProdutosEntity produtoAtualizado = produtosService.editar(id, dadosNovos);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean sucesso = produtosService.excluir(id);
        if (sucesso) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}