package com.vinicius.vs.associado.repository;

import com.vinicius.vs.associado.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssociadoRepository extends JpaRepository<Associado, Long> {
}
