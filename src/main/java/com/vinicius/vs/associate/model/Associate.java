package com.vinicius.vs.associate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinicius.vs.associate.dtos.CreateAssociateDTO;
import com.vinicius.vs.associate.dtos.RandomInfoDTO;
import com.vinicius.vs.vote.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Entity
@Table(name = "tb_associate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Cpf não pode ser nulo e deve conter 11 digitos.")
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "Nome não pode ser nulo")
    @Size(max = 50)
    private String name;

    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "associate")
    private List<Vote> votes = new ArrayList<>();

    public Associate(CreateAssociateDTO associadoDTO) {
        this.setCpf(associadoDTO.getCpf());
        this.setName(associadoDTO.getName());
    }

    public void update(RandomInfoDTO randomInfoDTO) {
        setIfNotNull(this::setLastName, randomInfoDTO.getLastName());
        setIfNotNull(this::setDateOfBirth, randomInfoDTO.getDateOfBirth());
        setIfNotNull(this::setPhoneNumber, randomInfoDTO.getPhoneNumber());
        setIfNotNull(this::setEmail, randomInfoDTO.getEmail());
    }

    static <T> void setIfNotNull(final Consumer<T> setter, final T value){
        if (value != null){
            setter.accept(value);
        }
    }
}
