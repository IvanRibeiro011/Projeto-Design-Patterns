package com.tads.biblioteca.dtos.response;

import com.tads.biblioteca.dtos.LivroDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AluguelResponseDTO {
    private Long id;

    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
    private LocalDate dataEstipulada;
    private Boolean pendente;
    private Double total;

    private Long alunoId;

}
