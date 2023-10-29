package com.projeto.concessionaria.service;

import com.projeto.concessionaria.model.ProdutoEntity;
import com.projeto.concessionaria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void cadastrarProduto(ProdutoEntity produtoEntity){
        this.produtoRepository.saveAndFlush(produtoEntity);
    }

    public List<ProdutoEntity> getProdutos(){
        return this.produtoRepository.findAll();
    }

    public Optional<ProdutoEntity> pesquisaProduto(Long id){
        return this.produtoRepository.findById(id);
    }

    public void deletarProduto(Long id){
        if(this.produtoRepository.findById(id).isPresent()){
            this.produtoRepository.deleteById(id);
        }
    }
}
