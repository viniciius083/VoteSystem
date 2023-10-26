package com.vinicius.vs.vote.dtos;

import com.vinicius.vs.agenda.enumeration.AgendaResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {

    private long associateId;

    private AgendaResult vote;

    private long agendaId;
}
