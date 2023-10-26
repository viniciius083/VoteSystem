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
public class VoteConfirmationDTO {

    private String name;

    private AgendaResult vote;

    private String subject;
}
