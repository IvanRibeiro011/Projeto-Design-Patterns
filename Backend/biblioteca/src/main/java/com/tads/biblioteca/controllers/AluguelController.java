package com.tads.biblioteca.controllers;

import com.tads.biblioteca.dtos.AluguelDTO;
import com.tads.biblioteca.dtos.CalculoDTO;
import com.tads.biblioteca.dtos.response.AluguelResponseDTO;
import com.tads.biblioteca.services.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelService service;

    @GetMapping("{id}")
    public ResponseEntity<AluguelResponseDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AluguelDTO>> findById(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }
//    @GetMapping("/findByName/{name}")
//    public ResponseEntity<List<AluguelDTO>> findByName(@PathVariable("name") String name) {
//        return new ResponseEntity<>(service.findByAlunoName(name), HttpStatus.OK);
//    }

    @PostMapping("/multa")
    public ResponseEntity<AluguelResponseDTO> calcularMulta(@RequestBody CalculoDTO dto) {
        return new ResponseEntity<>(service.calcularMulta(dto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AluguelDTO> insert(@RequestBody AluguelDTO dto) {
        return new ResponseEntity<>(service.insert(dto), HttpStatus.CREATED);
    }
}
