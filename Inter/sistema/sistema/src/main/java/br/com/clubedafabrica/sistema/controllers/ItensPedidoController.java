package br.com.clubedafabrica.sistema.controllers;

import br.com.clubedafabrica.sistema.entities.ItensPedidoEntity;
import br.com.clubedafabrica.sistema.services.ItensPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens-pedido")
@RequiredArgsConstructor
public class ItensPedidoController {

    private final ItensPedidoService itensPedidoService;

    @GetMapping
    public ResponseEntity<List<ItensPedidoEntity>> listarTodos() {
        List<ItensPedidoEntity> itens = itensPedidoService.listarTodos();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItensPedidoEntity> buscarPorId(@PathVariable Long id) {
        Optional<ItensPedidoEntity> item = itensPedidoService.buscarPorId(id);
        return item.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItensPedidoEntity> incluir(@RequestBody ItensPedidoEntity itemPedido) {
        ItensPedidoEntity novoItem = itensPedidoService.incluir(itemPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItensPedidoEntity> editar(@PathVariable Long id, @RequestBody ItensPedidoEntity dadosNovos) {
        ItensPedidoEntity itemAtualizado = itensPedidoService.editar(id, dadosNovos);
        if (itemAtualizado != null) {
            return ResponseEntity.ok(itemAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean sucesso = itensPedidoService.excluir(id);
        if (sucesso) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}