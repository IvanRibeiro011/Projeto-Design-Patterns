package com.tads.biblioteca.controllers;

import com.tads.biblioteca.dtos.AluguelDTO;
import com.tads.biblioteca.services.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelService service;
    @PostMapping
    public ResponseEntity<AluguelDTO> insert(@RequestBody AluguelDTO dto){
        return new ResponseEntity<>(service.insert(dto), HttpStatus.CREATED);
    }
}
