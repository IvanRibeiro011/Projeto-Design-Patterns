package com.tads.biblioteca.controllers;

import com.tads.biblioteca.dtos.AlunoDTO;
import com.tads.biblioteca.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private UsuarioService service;
    @GetMapping("{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<AlunoDTO>> findAllPage(Pageable pageable){
        return new ResponseEntity<>(service.findAllPage(pageable),HttpStatus.OK);
    }
}
