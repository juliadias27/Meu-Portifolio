package br.com.clubedafabrica.sistema.services;

import br.com.clubedafabrica.sistema.entities.ItensPedidoEntity;
import br.com.clubedafabrica.sistema.repositories.ItensPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItensPedidoService {

    private final ItensPedidoRepository itensPedidoRepository;

    public List<ItensPedidoEntity> listarTodos() {
        return itensPedidoRepository.findAll();
    }

    public Optional<ItensPedidoEntity> buscarPorId(Long id) {
        return itensPedidoRepository.findById(id);
    }

    public ItensPedidoEntity incluir(ItensPedidoEntity itemPedido) {
        return itensPedidoRepository.save(itemPedido);
    }

    public ItensPedidoEntity editar(Long id, ItensPedidoEntity dadosNovos) {
        Optional<ItensPedidoEntity> optionalItem = itensPedidoRepository.findById(id);

        if (optionalItem.isEmpty()) {
            return null;
        }

        ItensPedidoEntity itemExistente = optionalItem.get();
        itemExistente.setQtd(dadosNovos.getQtd());
        itemExistente.setValorUnitario(dadosNovos.getValorUnitario());
        itemExistente.setSubtotal(dadosNovos.getSubtotal());
        itemExistente.setProduto(dadosNovos.getProduto());

        return itensPedidoRepository.save(itemExistente);
    }

    public boolean excluir(Long id) {
        if (itensPedidoRepository.existsById(id)) {
            itensPedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}