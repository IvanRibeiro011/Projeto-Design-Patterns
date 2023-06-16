package com.tads.biblioteca.services;

import com.tads.biblioteca.dtos.LivroDTO;
import com.tads.biblioteca.entities.Livro;
import com.tads.biblioteca.exceptions.ResourceNotFoundException;
import com.tads.biblioteca.repositories.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Transactional(readOnly = true)
    public LivroDTO findById(Long id) {
        Livro livro = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
        return mapper.map(livro, LivroDTO.class);
    }
    @Transactional(readOnly = true)
    public Page<LivroDTO> findAllPage(Pageable pageable){
        Page<Livro> livros = repository.findAll(pageable);
        return livros.map(x -> mapper.map(x, LivroDTO.class));
    }


}
