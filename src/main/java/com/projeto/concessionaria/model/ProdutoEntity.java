package com.projeto.concessionaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@Table(name = "tb_produto")
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String descricao;

    @Column
    private Integer quantidade;

    @Column
    private BigDecimal valorUnitario;

//    @ManyToOne
//    @JoinColumn(name = "pedido_id")
//    private PedidoEntity pedidoEntity;

}
