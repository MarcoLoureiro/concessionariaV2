package com.projeto.concessionaria.service;

import com.projeto.concessionaria.model.PedidoEntity;
import com.projeto.concessionaria.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public void cadastrarPedido(PedidoEntity pedidoEntity){
        this.pedidoRepository.saveAndFlush(pedidoEntity);
    }

    public List<PedidoEntity> getPedidos(){
        return this.pedidoRepository.findAll();
    }

    public Optional<PedidoEntity> pesquisarPedido(Long id){
        return this.pedidoRepository.findById(id);
    }

    public void deletarPedido(Long id){
        if(pesquisarPedido(id).isPresent()){
            this.pedidoRepository.deleteById(id);
        }
    }
}
