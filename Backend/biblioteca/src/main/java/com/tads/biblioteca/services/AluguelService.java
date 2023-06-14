package com.tads.biblioteca.services;

import com.tads.biblioteca.dtos.AluguelDTO;
import com.tads.biblioteca.dtos.LivroDTO;
import com.tads.biblioteca.dtos.response.AluguelResponseDTO;
import com.tads.biblioteca.entities.Aluguel;
import com.tads.biblioteca.entities.Aluno;
import com.tads.biblioteca.entities.Livro;
import com.tads.biblioteca.repositories.AluguelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AluguelService {
    @Autowired
    private AluguelRepository repository;
    @Autowired
     private AlunoService alunoService;
    @Autowired
    private ModelMapper mapper;

    private AluguelResponseDTO findById(Long id){

    }
    @Transactional
    public AluguelDTO insert(AluguelDTO dto){
        Aluguel aluguel = new Aluguel();
        copyDTOToEntity(dto,aluguel);
        return mapper.map(repository.save(aluguel), AluguelDTO.class);
    }

    private void copyDTOToEntity(AluguelDTO dto,Aluguel aluguel){

        aluguel.setDataAluguel(LocalDate.now());
        Aluno aluno = mapper.map(alunoService.findById(dto.getAlunoId()),Aluno.class);
        aluguel.setAluno(aluno);

        for (LivroDTO obj : dto.getLivros()){
            Livro livro = new Livro();
            livro.setId(obj.getId());

            aluguel.getLivros().add(livro);
        }
    }
}
