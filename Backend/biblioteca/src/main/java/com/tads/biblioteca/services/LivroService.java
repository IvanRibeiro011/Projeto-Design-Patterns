package com.tads.biblioteca.services;

import com.tads.biblioteca.dtos.LivroDTO;
import com.tads.biblioteca.entities.Livro;
import com.tads.biblioteca.exceptions.EntityRegistrationException;
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
    public Livro findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
    }

    @Transactional(readOnly = true)
    public LivroDTO findByIdDTO(Long id) {
        Livro livro = findById(id);
        return mapper.map(livro, LivroDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<LivroDTO> findAllPage(Pageable pageable) {
        Page<Livro> livros = repository.findAll(pageable);
        return livros.map(x -> mapper.map(x, LivroDTO.class));
    }

    @Transactional
    public LivroDTO insert(LivroDTO dto) {
        if (!repository.existsByNome(dto.getNome())) {
            Livro livro = new Livro();
            copyDtoToEntity(livro, dto);
            return mapper.map(repository.save(livro), LivroDTO.class);
        } else {
            throw new EntityRegistrationException("Já existe um registro desta entidade cadastrada");
        }
    }

    @Transactional
    public LivroDTO update(LivroDTO dto) {
        Livro livro = findById(dto.getId());
        copyDtoToEntity(livro, dto);
        return mapper.map(repository.save(livro), LivroDTO.class);
    }

    private void copyDtoToEntity(Livro livro, LivroDTO dto) {
        livro.setAutor(dto.getAutor());
        livro.setNome(dto.getNome());
        livro.setDescricao(livro.getDescricao());
        livro.setDisponivel(dto.getDisponivel());
        livro.setQuantidade(dto.getQuantidade());
    }
}
