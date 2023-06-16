package com.tads.biblioteca.services;

import com.tads.biblioteca.dtos.AluguelDTO;
import com.tads.biblioteca.dtos.CalculoDTO;
import com.tads.biblioteca.dtos.LivroDTO;
import com.tads.biblioteca.dtos.response.AluguelResponseDTO;
import com.tads.biblioteca.entities.Aluguel;
import com.tads.biblioteca.entities.Aluno;
import com.tads.biblioteca.entities.Livro;
import com.tads.biblioteca.exceptions.InvalidCalculationTypeException;
import com.tads.biblioteca.exceptions.ResourceNotFoundException;
import com.tads.biblioteca.projections.AluguelProjection;
import com.tads.biblioteca.repositories.AluguelRepository;
import com.tads.biblioteca.strategy.CalculadorPadrao;
import com.tads.biblioteca.strategy.CalculadorPromocional;
import com.tads.biblioteca.strategy.interfaces.CalculadorDeMultas;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AluguelService {
    @Autowired
    private AluguelRepository repository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CalculadorPadrao calculadorPadrao;
    @Autowired
    private CalculadorPromocional calculadorPromocional;

    @Transactional(readOnly = true)
    public AluguelResponseDTO findById(Long id) {
        return mapper.map(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado")), AluguelResponseDTO.class);
    }

//    @Transactional(readOnly = true)
//    public List<AluguelDTO> findByAlunoName(String name) {
//        List<AluguelProjection> aluguelProjection = repository.findAluguelByAluno(name);
//        List<AluguelDTO> dtos = new ArrayList<>();
//        for(AluguelProjection p: aluguelProjection){
//            AluguelDTO dto = new AluguelDTO(p.getId(),p.getDataAluguel(),p.getDataDevolucao(),p.getDataEstipulada(),p.getLivroId());
//        }
//        return dtos;
//    }

    @Transactional(readOnly = true)
    public Page<AluguelDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(x -> mapper.map(x, AluguelDTO.class));
    }

    @Transactional
    public AluguelDTO insert(AluguelDTO dto) {
        Aluguel aluguel = new Aluguel();
        copyDTOToEntity(dto, aluguel);
        return mapper.map(repository.save(aluguel), AluguelDTO.class);
    }

    @Transactional
    public AluguelResponseDTO calcularMulta(CalculoDTO dto) {
        AluguelResponseDTO aluguelDTO = mapper.map(findById(dto.getId()), AluguelResponseDTO.class);

        if (dto.getTipo().equalsIgnoreCase("padrao")) {
            aluguelDTO.setTotal(calcularValor(aluguelDTO, calculadorPadrao));
            return aluguelDTO;
        } else if (dto.getTipo().equalsIgnoreCase("promo")) {
            aluguelDTO.setTotal(calcularValor(aluguelDTO, calculadorPromocional));
            return aluguelDTO;
        } else {
            throw new InvalidCalculationTypeException("Necessário tipo válido de tipo de calculo");
        }
    }

    private double calcularValor(AluguelResponseDTO aluguel, CalculadorDeMultas calculadorDeMultas) {
        int dias = aluguel.getDataDevolucao().compareTo(aluguel.getDataEstipulada());
        if (dias > 0) {
            return calculadorDeMultas.calcularMulta(dias);
        } else
            return 0.0;
    }

    private void copyDTOToEntity(AluguelDTO dto, Aluguel aluguel) {

        aluguel.setDataAluguel(LocalDate.now());
        aluguel.setDataEstipulada(aluguel.getDataAluguel().plusWeeks(1));
        if (dto.getDataDevolucao() != null) {
            aluguel.setDataDevolucao(dto.getDataDevolucao());
        }
        Aluno aluno = mapper.map(alunoService.findById(dto.getAlunoId()), Aluno.class);
        aluguel.setAluno(aluno);

        aluguel.getLivros().clear();

        for (LivroDTO obj : dto.getLivros()) {
            Livro livro = new Livro();
            livro.setId(obj.getId());

            aluguel.getLivros().add(livro);
        }
    }
}
