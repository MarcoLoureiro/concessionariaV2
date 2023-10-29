package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long> {
}
