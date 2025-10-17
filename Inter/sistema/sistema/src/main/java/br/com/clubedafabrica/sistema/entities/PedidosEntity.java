package br.com.clubedafabrica.sistema.entities;

import br.com.clubedafabrica.sistema.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime data;

    @Column(name = "valor_total_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotalCompra;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    // Relacionamento: Muitos pedidos para um usuário.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false) // Chave estrangeira
    @JsonBackReference
    private UsuariosEntity usuario;

    // Relacionamento: Um pedido tem muitos itens.
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItensPedidoEntity> itens;

}