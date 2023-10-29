package com.projeto.concessionaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tb_pedido")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer numeroPedido;

    @Column
    private String cliente;

    @Column
    private Integer quantidade;

    @OneToMany
    private List<ProdutoEntity> produtoEntityList;


}
