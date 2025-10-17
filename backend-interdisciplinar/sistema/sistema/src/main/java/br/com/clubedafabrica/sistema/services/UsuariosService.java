package br.com.clubedafabrica.sistema.services;

import br.com.clubedafabrica.sistema.entities.UsuariosEntity;
import br.com.clubedafabrica.sistema.repositories.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public List<UsuariosEntity> listarTodos() {
        return usuariosRepository.findAll();
    }

    public Optional<UsuariosEntity> buscarPorId(Long id) {
        return usuariosRepository.findById(id);
    }

    public UsuariosEntity incluir(UsuariosEntity usuario) {
        return usuariosRepository.save(usuario);
    }

    public UsuariosEntity editar(Long id, UsuariosEntity dadosNovos) {
        Optional<UsuariosEntity> optionalUsuario = usuariosRepository.findById(id);

        if (optionalUsuario.isEmpty()) {
            return null;
        }

        UsuariosEntity usuarioExistente = optionalUsuario.get();
        usuarioExistente.setNome(dadosNovos.getNome());
        usuarioExistente.setCpf(dadosNovos.getCpf());
        usuarioExistente.setEmail(dadosNovos.getEmail());
        usuarioExistente.setTelefone(dadosNovos.getTelefone());
        usuarioExistente.setStatus(dadosNovos.getStatus());
        usuarioExistente.setTipoUsuario(dadosNovos.getTipoUsuario());

        return usuariosRepository.save(usuarioExistente);
    }

    public boolean excluir(Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}