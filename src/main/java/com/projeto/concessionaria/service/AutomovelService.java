package com.projeto.concessionaria.service;

import com.projeto.concessionaria.model.AutomovelEntity;
import com.projeto.concessionaria.repository.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutomovelService {

    @Autowired
    private AutomovelRepository automovelRepository;
    public void cadastrarAutomovel(AutomovelEntity automovelEntity){
        this.automovelRepository.saveAndFlush(automovelEntity);
    }
    public List<AutomovelEntity> getAutomoveis(){
        return this.automovelRepository.findAll();
    }
    public void deletarAutomovel(Long id){
        if(this.automovelRepository.findById(id).isPresent()){
            this.automovelRepository.deleteById(id);
        }
    }

    public Optional<AutomovelEntity> pesquisaAutomovel(Long id){
        return this.automovelRepository.findById(id);
    }

}
