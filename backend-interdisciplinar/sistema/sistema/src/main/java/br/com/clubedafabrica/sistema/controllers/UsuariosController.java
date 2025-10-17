package br.com.clubedafabrica.sistema.controllers;

import br.com.clubedafabrica.sistema.entities.UsuariosEntity;
import br.com.clubedafabrica.sistema.services.UsuariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // 1. Anotação principal: Transforma a classe em um Controller REST.
@RequestMapping("/api/usuarios") // 2. URL base para todos os métodos nesta classe.
@RequiredArgsConstructor
public class UsuariosController {

    // Injeta o Service, que contém a lógica de negócio.
    private final UsuariosService usuariosService;

    // --- ENDPOINT PARA LISTAR TODOS OS USUÁRIOS ---
    @GetMapping // 3. Mapeia requisições HTTP GET para este método.
    public ResponseEntity<List<UsuariosEntity>> listarTodos() {
        List<UsuariosEntity> usuarios = usuariosService.listarTodos();
        return ResponseEntity.ok(usuarios); // Retorna status 200 OK com a lista no corpo.
    }

    // --- ENDPOINT PARA BUSCAR UM USUÁRIO POR ID ---
    // Exemplo de URL: /api/usuarios/1
    @GetMapping("/{id}") // O {id} é uma variável na URL.
    public ResponseEntity<UsuariosEntity> buscarPorId(@PathVariable Long id) { // 4. @PathVariable pega o {id} da URL.
        Optional<UsuariosEntity> usuario = usuariosService.buscarPorId(id);
        
        // Se o usuário foi encontrado, retorna 200 OK. Se não, retorna 404 Not Found.
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- ENDPOINT PARA CRIAR UM NOVO USUÁRIO ---
    @PostMapping // 5. Mapeia requisições HTTP POST.
    public ResponseEntity<UsuariosEntity> incluir(@RequestBody UsuariosEntity usuario) { // 6. @RequestBody pega o JSON do corpo da requisição.
        UsuariosEntity novoUsuario = usuariosService.incluir(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario); // Retorna status 201 Created.
    }

    // --- ENDPOINT PARA ATUALIZAR UM USUÁRIO EXISTENTE ---
    // Exemplo de URL: /api/usuarios/1
    @PutMapping("/{id}") // 7. Mapeia requisições HTTP PUT.
    public ResponseEntity<UsuariosEntity> editar(@PathVariable Long id, @RequestBody UsuariosEntity dadosNovos) {
        UsuariosEntity usuarioAtualizado = usuariosService.editar(id, dadosNovos);
        
        if (usuarioAtualizado != null) {
            return ResponseEntity.ok(usuarioAtualizado); // Retorna 200 OK.
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se o usuário não existir.
    }

    // --- ENDPOINT PARA EXCLUIR UM USUÁRIO ---
    // Exemplo de URL: /api/usuarios/1
    @DeleteMapping("/{id}") // 8. Mapeia requisições HTTP DELETE.
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean sucesso = usuariosService.excluir(id);
        
        if (sucesso) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content (padrão para delete com sucesso).
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se não encontrou para excluir.
    }
}