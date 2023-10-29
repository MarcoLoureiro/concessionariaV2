package com.projeto.concessionaria.dto;

import com.projeto.concessionaria.model.ProdutoEntity;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class PedidoDTO {


    @NonNull
    @Range(min = 4,message = "Pedido precisa ter no mínimo 4 digitos")
    @Schema(name = "numeroPedido",example = "1234")
    private Integer numeroPedido;

    @NotBlank
    @Length(min = 1,message = "Nome do cliente precisa ter no mínimo 1 caractere")
    @Schema(name = "cliente",pattern = "Chevrolet da Silva Ford")
    private String cliente;

    @NonNull
    @Range(min = 1,message = "Quantidade do pedido precisa ser no mínimo 1")
    @Schema(name = "quantidade",example = "5")
    private Integer quantidade;

    @NotBlank
    @Size(min = 1,message = "Precisa haver no mínimo um produto ao realizar um pedido")
    @ArraySchema(schema = @Schema(implementation = ProdutoEntity.class, example = "[{ \"id\": 1, \"descricao\": \"Caneta Incrível de Platina\", \"quantidade\": 10, \"valorUnitario\": 10.0 }]"))
    private List<ProdutoEntity> produtoEntityList;
}
