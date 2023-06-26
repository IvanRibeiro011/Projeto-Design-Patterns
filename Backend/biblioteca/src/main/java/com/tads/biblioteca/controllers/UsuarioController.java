package com.tads.biblioteca.controllers;

import com.tads.biblioteca.dtos.UsuarioDTO;
import com.tads.biblioteca.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @GetMapping("{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAllPage(Pageable pageable){
        return new ResponseEntity<>(service.findAllPage(pageable),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO dto){
        return new ResponseEntity<>(service.cadastrarUsuario(dto),HttpStatus.CREATED);
    }
}
