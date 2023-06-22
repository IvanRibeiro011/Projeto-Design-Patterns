package com.tads.biblioteca.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AluguelMinDTO {
    private Long id;

    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
    private LocalDate dataEstipulada;
    private Boolean pendente;

    private String alunoNome;

    private List<LivroMinDTO> livros = new ArrayList<>();

    public void addLivro(LivroMinDTO dto){
        livros.add(dto);
    }
}
