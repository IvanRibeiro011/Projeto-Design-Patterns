package com.tads.biblioteca.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class LivroDTO {

    private Long id;
    private String nome;
    private String autor;
    private String descricao;
    private Integer quantidade;
    private Boolean disponivel;

}
