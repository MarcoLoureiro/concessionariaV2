package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.model.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity,Long> {
}
