package com.tads.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "tb_livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String autor;
    private String description;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
    @Transient
    @ManyToMany(mappedBy = "livros")
    private Set<Aluno> alunos = new HashSet<>();
}
