package com.vinicius.vs.vote.dtos;

import com.vinicius.vs.agenda.enumeration.AgendaResult;
import com.vinicius.vs.vote.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotesDTO {

    private AgendaResult vote;

    private String name;

    public VotesDTO(Vote vote) {
        this.setVote(vote.getVote());
        this.setName(vote.getAssociate().getName());
    }
}
