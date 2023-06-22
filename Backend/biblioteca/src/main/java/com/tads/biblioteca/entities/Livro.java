package com.tads.biblioteca.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_livro")
public class Livro {
    @Id
    @Column(name = "livro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String autor;
    private String descricao;
    private Integer quantidade;
    private Boolean disponivel;
    @ManyToMany(mappedBy = "livros")
    private Set<Aluguel> alugueis = new HashSet<>();
}
