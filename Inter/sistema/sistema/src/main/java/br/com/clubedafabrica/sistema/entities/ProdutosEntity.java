package br.com.clubedafabrica.sistema.entities;

import br.com.clubedafabrica.sistema.enums.StatusProduto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "quant_estoque", nullable = false)
    private Integer quantEstoque;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProduto status;

    // Relacionamento: Muitos produtos para uma categoria.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false) // Chave estrangeira
    @JsonBackReference
    private CategoriasEntity categoria;
    
    // Relacionamento: Um produto pode estar em muitos itens de pedido.
    // Usamos @JsonIgnore para não trazer a lista de pedidos ao consultar um produto.
    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ItensPedidoEntity> itensPedido;
}