package com.projeto.concessionaria.dto;

import com.projeto.concessionaria.model.PedidoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ProdutoDTO {


    @NotBlank
    @Schema(name = "descricao",pattern = "Caneta Azul")
    @Length(min = 1,message = "Descrição precisa ter no mínimo 1 caractere")
    private String descricao;

    @Schema(name = "quantidade",example = "2")
    @NotNull(message = "Quantidade não pode ser vazia")
    @Range(min = 1, message = "Quantidade não pode ser menor que 1")
    private Integer quantidade;

    @Schema(name = "valorUnitario",example = "20.99")
    @NotNull(message = "Valor unitário não pode ser vazio")
    @DecimalMin(value = "0.1",message = "Valor mínimo permitido para o produto é 0.1")
    private BigDecimal valorUnitario;

}
