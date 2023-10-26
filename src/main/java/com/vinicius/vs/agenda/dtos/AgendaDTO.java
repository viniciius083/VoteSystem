package com.vinicius.vs.agenda.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinicius.vs.agenda.enumeration.AgendaResult;
import com.vinicius.vs.agenda.model.Agenda;
import com.vinicius.vs.vote.dtos.VotesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaDTO {

    private long id;

    private String subject;

    private AgendaResult result;

    private int quantityVotes;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime openVote;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime closeVote;


    private List<VotesDTO> votes = new ArrayList<>();



    public AgendaDTO(Agenda agenda) {
        this.setId(agenda.getId());
        this.setSubject(agenda.getSubject());
        this.setResult(agenda.getResult());
        this.setQuantityVotes(agenda.getQuantityVotes());
        this.setOpenVote(agenda.getOpenVote());
        this.setCloseVote(agenda.getCloseVote());
        this.getVotes().addAll(agenda.getVotes().stream().map(VotesDTO::new).collect(Collectors.toList()));
    }
}
