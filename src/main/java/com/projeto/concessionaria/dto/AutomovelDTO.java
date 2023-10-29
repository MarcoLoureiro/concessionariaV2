package com.projeto.concessionaria.dto;

import com.projeto.concessionaria.model.PessoaEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class AutomovelDTO {

    @Schema(name = "placa",pattern = "OVO-D0RM1R")
    @Length(min = 5)
    @NotBlank(message = "Necessário uma placa de veículo com no mínimo 5 digitos")
    private String placa;

    @Schema(name = "veiculo", pattern = "Opala Ultra Super Turbo 1968")
    @Length(min = 1)
    @NotBlank(message = "Modelo de veículo não pode ser vazio")
    private String veiculo;

//    @Schema(name = "dono",implementation = PessoaEntity.class,example = "{ \"id\" : 1}")
//    @NotBlank
//    private PessoaEntity dono;
}
