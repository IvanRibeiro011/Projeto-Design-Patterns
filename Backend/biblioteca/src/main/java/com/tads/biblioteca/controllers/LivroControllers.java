package com.tads.biblioteca.controllers;

import com.tads.biblioteca.dtos.LivroDTO;
import com.tads.biblioteca.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroControllers {
    @Autowired
    private LivroService service;

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findByIdDTO(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<LivroDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAllPage(pageable), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<LivroDTO> insert(@RequestBody LivroDTO dto){
        return new ResponseEntity<>(service.insert(dto),HttpStatus.OK);
    }
}
