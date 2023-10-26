package com.vinicius.vs.associate.repository;

import com.vinicius.vs.associate.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssociateRepository extends JpaRepository<Associate, Long> {
}
