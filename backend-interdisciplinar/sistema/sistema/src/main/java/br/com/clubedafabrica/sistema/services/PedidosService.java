package br.com.clubedafabrica.sistema.services;

import br.com.clubedafabrica.sistema.entities.PedidosEntity;
import br.com.clubedafabrica.sistema.repositories.PedidosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidosService {

    private final PedidosRepository pedidosRepository;

    public List<PedidosEntity> listarTodos() {
        return pedidosRepository.findAll();
    }

    public Optional<PedidosEntity> buscarPorId(Long id) {
        return pedidosRepository.findById(id);
    }

    public PedidosEntity incluir(PedidosEntity pedido) {
        // Lógica de negócio mais complexa pode ser adicionada aqui,
        // como calcular o valor total a partir dos itens.
        return pedidosRepository.save(pedido);
    }

    public PedidosEntity editar(Long id, PedidosEntity dadosNovos) {
        Optional<PedidosEntity> optionalPedido = pedidosRepository.findById(id);

        if (optionalPedido.isEmpty()) {
            return null;
        }

        PedidosEntity pedidoExistente = optionalPedido.get();
        // Geralmente, só se edita o status de um pedido.
        // Outros campos como data, valor e usuário não costumam ser alterados.
        pedidoExistente.setStatus(dadosNovos.getStatus());
        
        return pedidosRepository.save(pedidoExistente);
    }

    public boolean excluir(Long id) {
        if (pedidosRepository.existsById(id)) {
            pedidosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}