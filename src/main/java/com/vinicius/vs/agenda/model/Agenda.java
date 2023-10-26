package com.vinicius.vs.agenda.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinicius.vs.agenda.dtos.CreateAgendaDTO;
import com.vinicius.vs.agenda.enumeration.AgendaResult;
import com.vinicius.vs.vote.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_agenda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String subject;

    @Enumerated(EnumType.STRING)
    private AgendaResult result;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime openVote;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime closeVote;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "agenda")
    private List<Vote> votes = new ArrayList<>();

    private int quantityVotes = 0;


    public Agenda(CreateAgendaDTO pautaLiteDTO) {
        this.setSubject(pautaLiteDTO.getSubject());
    }

}
