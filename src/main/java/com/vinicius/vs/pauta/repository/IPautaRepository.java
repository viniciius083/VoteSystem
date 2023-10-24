package com.vinicius.vs.pauta.repository;

import com.vinicius.vs.pauta.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPautaRepository extends JpaRepository<Pauta, Long> {
}
