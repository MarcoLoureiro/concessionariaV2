package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.model.AutomovelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomovelRepository extends JpaRepository<AutomovelEntity,Long> {
}
