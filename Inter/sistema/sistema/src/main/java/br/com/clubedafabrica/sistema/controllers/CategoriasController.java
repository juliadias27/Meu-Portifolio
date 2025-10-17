package br.com.clubedafabrica.sistema.controllers;

import br.com.clubedafabrica.sistema.entities.CategoriasEntity; // <-- MUDOU
import br.com.clubedafabrica.sistema.services.CategoriasService;   // <-- MUDOU
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias") // <-- MUDOU A URL
@RequiredArgsConstructor
public class CategoriasController { // <-- MUDOU

    private final CategoriasService categoriasService; // <-- MUDOU

    @GetMapping
    public ResponseEntity<List<CategoriasEntity>> listarTodas() { // <-- MUDOU O TIPO
        List<CategoriasEntity> categorias = categoriasService.listarTodas(); // <-- MUDOU
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasEntity> buscarPorId(@PathVariable Long id) { // <-- MUDOU O TIPO
        Optional<CategoriasEntity> categoria = categoriasService.buscarPorId(id); // <-- MUDOU
        return categoria.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriasEntity> incluir(@RequestBody CategoriasEntity categoria) { // <-- MUDOU O TIPO
        CategoriasEntity novaCategoria = categoriasService.incluir(categoria); // <-- MUDOU
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriasEntity> editar(@PathVariable Long id, @RequestBody CategoriasEntity dadosNovos) { // <-- MUDOU O TIPO
        CategoriasEntity categoriaAtualizada = categoriasService.editar(id, dadosNovos); // <-- MUDOU
        if (categoriaAtualizada != null) {
            return ResponseEntity.ok(categoriaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean sucesso = categoriasService.excluir(id); // <-- MUDOU
        if (sucesso) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}