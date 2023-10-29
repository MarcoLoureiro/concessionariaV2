package com.projeto.concessionaria.service;

import com.projeto.concessionaria.model.PessoaEntity;
import com.projeto.concessionaria.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaEntity> getPessoas(){
        return this.pessoaRepository.findAll();
    }

    public void cadastrarPessoa(PessoaEntity pessoaEntity){
        this.pessoaRepository.saveAndFlush(pessoaEntity);
    }

    public void deletarPessoa(Long id){
        if(pesquisarPessoa(id).isPresent()){
            this.pessoaRepository.deleteById(id);
        }
    }

    public Optional<PessoaEntity> pesquisarPessoa(Long id){
        return this.pessoaRepository.findById(id);
    }

}
