package br.com.clubedafabrica.sistema.controllers;

import br.com.clubedafabrica.sistema.entities.PedidosEntity;
import br.com.clubedafabrica.sistema.services.PedidosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidosController {

    private final PedidosService pedidosService;

    @GetMapping
    public ResponseEntity<List<PedidosEntity>> listarTodos() {
        List<PedidosEntity> pedidos = pedidosService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidosEntity> buscarPorId(@PathVariable Long id) {
        Optional<PedidosEntity> pedido = pedidosService.buscarPorId(id);
        return pedido.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidosEntity> incluir(@RequestBody PedidosEntity pedido) {
        PedidosEntity novoPedido = pedidosService.incluir(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidosEntity> editar(@PathVariable Long id, @RequestBody PedidosEntity dadosNovos) {
        PedidosEntity pedidoAtualizado = pedidosService.editar(id, dadosNovos);
        if (pedidoAtualizado != null) {
            return ResponseEntity.ok(pedidoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean sucesso = pedidosService.excluir(id);
        if (sucesso) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}