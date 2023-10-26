package com.vinicius.vs.agenda.repository;

import com.vinicius.vs.agenda.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgendaRepository extends JpaRepository<Agenda, Long> {
}
