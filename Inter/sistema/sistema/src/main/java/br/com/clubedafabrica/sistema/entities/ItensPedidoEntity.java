package br.com.clubedafabrica.sistema.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_itens_pedido")
    private Long idItensPedido;

    @Column(nullable = false)
    private Integer qtd;

    @Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    // Relacionamento: Muitos itens para um pedido.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false) // Chave estrangeira para Pedidos
    @JsonBackReference
    private PedidosEntity pedido;

    // Relacionamento: Muitos itens podem se referir ao mesmo produto.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false) // Chave estrangeira para Produtos
    private ProdutosEntity produto;

}