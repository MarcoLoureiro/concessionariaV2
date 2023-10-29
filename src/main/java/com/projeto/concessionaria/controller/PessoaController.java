package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.dto.PessoaDTO;
import com.projeto.concessionaria.model.PessoaEntity;
import com.projeto.concessionaria.service.PessoaService;
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
@RequestMapping("api/pessoa")
public class PessoaController {


    @Autowired
    private PessoaService pessoaService;


    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Pessoa cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400",description = "Erro ao cadastrar pessoa")
    })

    @Operation(summary = "Cadastrar nova pessoa")
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarPessoa(@Valid @RequestBody PessoaDTO pessoaDTO){

        try{
            PessoaEntity pessoa = new PessoaEntity();
            pessoa.setCpf(pessoaDTO.getCpf());
            pessoa.setNome(pessoaDTO.getNome());
            pessoa.setEstado(pessoaDTO.getEstado());
            if(pessoaDTO.getAutomovelEntity() != null) {
                pessoa.setAutomovelEntity(pessoaDTO.getAutomovelEntity());
            }
            this.pessoaService.cadastrarPessoa(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch (Exception e){
            System.err.println("Erro ao cadastrar nova pessoa "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Lista de pessoas cadastradas"),
            @ApiResponse(responseCode = "204",description = "Sem conteúdo cadastrado para exibir")
    })
    @Operation(summary = "Listar pessoas cadastradas")
    @GetMapping("/listar")
    public ResponseEntity listarPessoas(){
        List<PessoaEntity> pessoaEntityList = this.pessoaService.getPessoas();
        if(pessoaEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(pessoaEntityList);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "20o",description = "Pessoa deletada com sucesso!"),
            @ApiResponse(responseCode = "406",description = "Erro ao deletar pessoa")
    })
    @Operation(summary = "Deletar pessoa")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarPessoa(@RequestParam Long id){
        if(this.pessoaService.pesquisarPessoa(id).isPresent()){
            this.pessoaService.deletarPessoa(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Pessoa alterada com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao alterar dados da pessoa")
    })
    @Operation(summary = "Alterar dados da pessoa")
    @PutMapping("/alterar/{id}")
    public ResponseEntity alterarPessoa(@PathVariable("id") Long id,@RequestBody PessoaDTO pessoaDTO){
        Optional<PessoaEntity> pessoaEntity = this.pessoaService.pesquisarPessoa((Long) id);
        if(pessoaEntity.isPresent()){
            try{
                PessoaEntity pessoaEntityAtualizada = new PessoaEntity();
                pessoaEntityAtualizada.setId(pessoaEntity.get().getId());
                pessoaEntityAtualizada.setCpf(pessoaDTO.getCpf());
                pessoaEntityAtualizada.setEstado(pessoaDTO.getEstado());
                pessoaEntityAtualizada.setNome(pessoaDTO.getNome());
                if(pessoaDTO.getAutomovelEntity() != null) {
                    pessoaEntityAtualizada.setAutomovelEntity(pessoaDTO.getAutomovelEntity());
                }
                this.pessoaService.cadastrarPessoa(pessoaEntityAtualizada);
                return ResponseEntity.status(HttpStatus.OK).build();
            }catch (Exception e){
                System.err.println("Erro ao alterar dados da pessoa "+e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }




}
