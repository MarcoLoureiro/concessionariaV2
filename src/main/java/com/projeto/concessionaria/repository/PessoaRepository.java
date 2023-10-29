package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.model.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
}
