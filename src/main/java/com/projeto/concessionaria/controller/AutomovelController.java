package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.dto.AutomovelDTO;
import com.projeto.concessionaria.model.AutomovelEntity;
import com.projeto.concessionaria.service.AutomovelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/automovel")
public class AutomovelController {
    @Autowired
    private AutomovelService automovelService;

    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Automóvel cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400",description = "Erro ao cadastrar veículo")
    })
    @Operation(summary = "Cadastrar novo automóvel")
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarAutomovel(@RequestBody AutomovelDTO automovelDTO){
        try{
            AutomovelEntity automovelEntity = new AutomovelEntity();
            automovelEntity.setPlaca(automovelDTO.getPlaca());
            automovelEntity.setVeiculo(automovelDTO.getVeiculo());

            this.automovelService.cadastrarAutomovel(automovelEntity);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e) {
            System.err.println("Erro ao cadastrar automóvel "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Lista de veículos cadastrados"),
            @ApiResponse(responseCode = "204",description = "Sem conteúdo cadastrado para exibir")
    })
    @Operation(summary = "Listar automóveis cadastrados")
    @GetMapping("/listar")
    public ResponseEntity<List<AutomovelEntity>> getAutomoveis(){
        List<AutomovelEntity> automovelEntityList = this.automovelService.getAutomoveis();
        if(automovelEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(automovelEntityList);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Automóvel deletado com sucesso!"),
            @ApiResponse(responseCode = "406",description = "Erro ao deletar automóvel")
    })
    @Operation(summary = "Deletar automóvel")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarAutomovel(@PathVariable("id") Long id){
        if(this.automovelService.pesquisaAutomovel((Long) id).isPresent()){
            this.automovelService.deletarAutomovel((Long) id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Automóvel alterado com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao alterar dados do automóvel")
    })
    @Operation(summary = "Alterar dados do automóvel")
    @PutMapping("/alterar/{id}")
    public ResponseEntity alterarAutomovel(@PathVariable("id")Long id,@Valid @RequestBody AutomovelDTO automovelDTO){

        Optional<AutomovelEntity> automovelPesquisado = this.automovelService.pesquisaAutomovel((Long) id);

        if(automovelPesquisado.isPresent()){
            try {
                AutomovelEntity automovel = new AutomovelEntity();
                automovel.setId(automovelPesquisado.get().getId());
                automovel.setPlaca(automovelDTO.getPlaca());
                this.automovelService.cadastrarAutomovel(automovel);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (Exception e){
                System.err.println("Erro ao alterar automóvel "+e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
