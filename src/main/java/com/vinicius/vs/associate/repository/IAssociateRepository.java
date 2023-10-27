package com.vinicius.vs.associate.repository;

import com.vinicius.vs.associate.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

@Repository
public interface IAssociateRepository extends JpaRepository<Associate, Long> {
    Optional<Associate> findByCpf(@NotBlank @Size(min = 11, max = 11) String cpf);
}
