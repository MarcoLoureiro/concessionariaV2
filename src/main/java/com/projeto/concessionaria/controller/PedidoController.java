package com.projeto.concessionaria.controller;


import com.projeto.concessionaria.dto.PedidoDTO;
import com.projeto.concessionaria.model.PedidoEntity;
import com.projeto.concessionaria.model.ProdutoEntity;
import com.projeto.concessionaria.service.PedidoService;
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
@RequestMapping("api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoService produtoService;

    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Pedido cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400",description = "Erro ao cadastrar pedido")
    })
    @Operation(summary = "Cadastrar novo pedido")
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarPedido(@Valid @RequestBody PedidoDTO pedidoDTO){
        try {
            PedidoEntity pedidoEntity = new PedidoEntity();

            pedidoEntity.setNumeroPedido(pedidoDTO.getNumeroPedido());
            pedidoEntity.setProdutoEntityList(pedidoDTO.getProdutoEntityList());
            pedidoEntity.setQuantidade(pedidoDTO.getQuantidade());
            pedidoEntity.setCliente(pedidoDTO.getCliente());

            this.pedidoService.cadastrarPedido(pedidoEntity);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch (Exception e){
            System.err.println("Erro ao cadastrar pedido "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Lista de pedidos cadastrados"),
            @ApiResponse(responseCode = "204",description = "Sem pedido cadastrado para exibir")
    })
    @Operation(summary = "Listar pedidos cadastrados")
    @GetMapping("/listar")
    public ResponseEntity<List<PedidoEntity>> getPedidos(){
        List<PedidoEntity> pedidoEntityList = this.pedidoService.getPedidos();

        if(pedidoEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoEntityList);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Pedido alterado com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao alterar dados do pedido")
    })
    @Operation(summary = "Alterar dados do pedido")
    @PutMapping("/alterar/{id}")
    public ResponseEntity atualizarPedido(@PathVariable("id") Long id,@Valid @RequestBody PedidoDTO pedidoDTO){
        Optional<PedidoEntity> pedidoEntity = this.pedidoService.pesquisarPedido((Long) id);
        if(pedidoEntity.isPresent()){
            try {
                PedidoEntity pedidoAtualizado = new PedidoEntity();
                pedidoAtualizado.setId(pedidoEntity.get().getId());
                pedidoAtualizado.setNumeroPedido(pedidoDTO.getNumeroPedido());
                pedidoAtualizado.setCliente(pedidoDTO.getCliente());
                pedidoAtualizado.setQuantidade(pedidoDTO.getQuantidade());
                pedidoAtualizado.setProdutoEntityList(pedidoDTO.getProdutoEntityList());
                this.pedidoService.cadastrarPedido(pedidoAtualizado);
                return ResponseEntity.status(HttpStatus.OK).build();
            }catch (Exception e){
                System.err.println("Erro ao atualizar pedido "+e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Pedido deletado com sucesso!"),
            @ApiResponse(responseCode = "406",description = "Erro ao deletar pedido")
    })
    @Operation(summary = "Deletar pedido")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarPedido(@PathVariable("id") Long id){
        if(this.pedidoService.pesquisarPedido((Long) id).isPresent()){
            this.pedidoService.deletarPedido((Long) id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

}
