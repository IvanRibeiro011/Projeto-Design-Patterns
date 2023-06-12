package com.tads.biblioteca.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class AlunoDTO {
    private Long id;
    private String nome;
    private String matricula;
}
