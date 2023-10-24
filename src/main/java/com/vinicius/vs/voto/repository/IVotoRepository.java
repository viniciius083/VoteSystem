package com.vinicius.vs.voto.repository;

import com.vinicius.vs.voto.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVotoRepository extends JpaRepository<Voto, Long> {
}
