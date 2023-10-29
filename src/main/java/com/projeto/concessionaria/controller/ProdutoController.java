package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.dto.ProdutoDTO;
import com.projeto.concessionaria.model.ProdutoEntity;
import com.projeto.concessionaria.service.ProdutoService;
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
@RequestMapping("api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Produto cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400",description = "Erro ao cadastrar produto")
    })
    @Operation(summary = "Cadastrar novo produto")
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarProduto(@Valid @RequestBody ProdutoDTO produtoDTO){
        try{
            ProdutoEntity produtoEntity = new ProdutoEntity();
            produtoEntity.setValorUnitario(produtoDTO.getValorUnitario());
            produtoEntity.setQuantidade(produtoDTO.getQuantidade());
            produtoEntity.setDescricao(produtoDTO.getDescricao());
            this.produtoService.cadastrarProduto(produtoEntity);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            System.err.println("Erro ao cadastrar produto "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Lista de produtos cadastrados"),
            @ApiResponse(responseCode = "204",description = "Sem conteúdo cadastrado para exibir")
    })
    @Operation(summary = "Listar produtos cadastrados")
    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoEntity>> getProdutos(){
        List<ProdutoEntity> produtoEntityList = this.produtoService.getProdutos();
        if(produtoEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(produtoEntityList);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "20o",description = "Produto deletado com sucesso!"),
            @ApiResponse(responseCode = "406",description = "Erro ao deletar produto")
    })
    @Operation(summary = "Deletar produto")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarProduto(@PathVariable("id") Long id){
        if(this.produtoService.pesquisaProduto((Long) id).isPresent()){
            this.produtoService.deletarProduto((Long) id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Produto alterado com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao alterar dados do produto")
    })
    @Operation(summary = "Alterar dados do produto")
    @PutMapping("/alterar/{id}")
    public ResponseEntity alterarProduto(@PathVariable("id") Long id,@Valid @RequestBody ProdutoDTO produtoDTO){
        Optional<ProdutoEntity> produtoEntityOptional = this.produtoService.pesquisaProduto((Long) id);
        if(produtoEntityOptional.isPresent()){
            try {
                ProdutoEntity produtoEntity = new ProdutoEntity();
                produtoEntity.setId(produtoEntityOptional.get().getId());
                produtoEntity.setValorUnitario(produtoDTO.getValorUnitario());
                produtoEntity.setQuantidade(produtoDTO.getQuantidade());
                produtoEntity.setDescricao(produtoDTO.getDescricao());
                this.produtoService.cadastrarProduto(produtoEntity);
                return ResponseEntity.status(HttpStatus.OK).build();
            }catch (Exception e){
                System.err.println("Erro ao alterar produto "+e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        }
    }

}
