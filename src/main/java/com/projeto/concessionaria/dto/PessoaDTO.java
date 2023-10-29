package com.projeto.concessionaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projeto.concessionaria.model.AutomovelEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDTO {

    @Schema(name = "nome",pattern = "Chevrolet da Silva Ford")
    @NotBlank
    @Length(min = 1,message = "Nome da pessoa precisa conter no mínimo 1 caractere")
    private String nome;

    @Schema(name = "cpf",pattern = "00300400587")
    @NotBlank
    @Length(min = 1,message = "Cpf não pode ser vazio")
    private String cpf;

    @Schema(name = "estado",pattern = "PA")
    @NotBlank(message = "Estado não pode ser vazio e deve conter no máximo 2 caracteres")
    @Length(min = 2,max = 2)
    private String estado;

    @Schema(name = "automovelEntity",implementation = AutomovelEntity.class,example = "{ \"id\" : 1}")
    private AutomovelEntity automovelEntity;
}
