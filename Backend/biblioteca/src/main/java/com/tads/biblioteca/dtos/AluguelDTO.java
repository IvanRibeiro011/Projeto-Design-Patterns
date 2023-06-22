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
public class AluguelDTO {
    private Long id;

    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
    private LocalDate dataEstipulada;
    private Boolean pendente;

    private Long alunoId;

    private List<LivroDTO> livros = new ArrayList<>();
}
