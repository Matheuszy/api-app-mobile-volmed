package com.codexsystem.api.domain.model;

import com.codexsystem.api.domain.enums.Especialidade;
import com.codexsystem.api.domain.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medicos")
@Getter
@Setter
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;
    private String telefone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Medico() {

    }

    public Medico(String nome, String email, Especialidade especialidade, String crm, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.status = Status.ATIVO;
        this.especialidade = especialidade;
        this.crm = crm;
        this.endereco = endereco;
    }


}
