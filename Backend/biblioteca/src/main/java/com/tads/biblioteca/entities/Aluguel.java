package com.tads.biblioteca.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_aluguel")
public class Aluguel {
    @Id
    @Column(name = "aluguel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataAluguel;
    private LocalDate dataEstipulada;
    private LocalDate dataDevolucao;
    private Boolean pendente;
    @ManyToOne
    @JoinColumn(name = "a_id")
    private Aluno aluno;

    @ManyToMany
    @JoinTable(name = "tb_aluguel_livro", joinColumns = @JoinColumn(name = "aluguel_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private Set<Livro> livros = new HashSet<>();

}
