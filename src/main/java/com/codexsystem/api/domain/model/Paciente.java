package com.codexsystem.api.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
public class Paciente {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String telefone;

    private String cpf;

    private String email;

    @Enumerated(EnumType.STRING)
    private Endereco endereco;

    public Paciente() {
    }

    public Paciente(String nome, String sobrenome, String telefone, String cpf, String email, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
    }

}
