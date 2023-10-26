package com.vinicius.vs.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinicius.vs.associate.model.Associate;
import com.vinicius.vs.agenda.enumeration.AgendaResult;
import com.vinicius.vs.agenda.model.Agenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AgendaResult vote;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "associate_id")
    private Associate associate;


    public Vote(Agenda agenda, Associate associate, AgendaResult vote) {
        this.setVote(vote);
        this.setAgenda(agenda);
        this.setAssociate(associate);
    }
}
