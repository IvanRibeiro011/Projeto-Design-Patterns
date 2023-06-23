package com.tads.biblioteca.services;

import com.tads.biblioteca.dtos.AlunoDTO;
import com.tads.biblioteca.entities.Usuario;
import com.tads.biblioteca.exceptions.ResourceNotFoundException;
import com.tads.biblioteca.repositories.AlunoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Transactional(readOnly = true)
    public AlunoDTO findById(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return mapper.map(usuario, AlunoDTO.class);
    }
    @Transactional(readOnly = true)
    public Page<AlunoDTO> findAllPage(Pageable pageable){
        Page<Usuario> alunos = repository.findAll(pageable);
        return alunos.map(x -> mapper.map(x, AlunoDTO.class));
    }
}
