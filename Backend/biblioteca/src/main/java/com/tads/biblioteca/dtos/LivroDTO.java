package com.tads.biblioteca.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@RequiredArgsConstructor
public class LivroDTO {

    private Long id;
    private String nome;
    private String autor;
    private String description;

}
