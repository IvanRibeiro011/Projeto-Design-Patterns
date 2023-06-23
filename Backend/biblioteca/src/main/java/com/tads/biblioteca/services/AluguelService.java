package com.tads.biblioteca.services;

import com.tads.biblioteca.dtos.AluguelDTO;
import com.tads.biblioteca.dtos.AluguelMinDTO;
import com.tads.biblioteca.dtos.CalculoDTO;
import com.tads.biblioteca.dtos.LivroDTO;
import com.tads.biblioteca.dtos.response.AluguelResponseDTO;
import com.tads.biblioteca.entities.Aluguel;
import com.tads.biblioteca.entities.Usuario;
import com.tads.biblioteca.entities.Livro;
import com.tads.biblioteca.exceptions.EntityNotAvailableException;
import com.tads.biblioteca.exceptions.InvalidCalculationTypeException;
import com.tads.biblioteca.exceptions.ResourceNotFoundException;
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
import java.util.List;

@Service
public class AluguelService {
    @Autowired
    private AluguelRepository repository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private LivroService livroService;
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

    @Transactional(readOnly = true)
    public List<AluguelMinDTO> findByAlunoName(String name) {
        List<Aluguel> aluguels = repository.findAluguelByAluno(name);
        return aluguels.stream().map(x -> mapper.map(x, AluguelMinDTO.class)).toList();
    }

    @Transactional(readOnly = true)
    public Page<AluguelDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(x -> mapper.map(x, AluguelDTO.class));
    }

    @Transactional
    public AluguelDTO insert(AluguelDTO dto) {
        Aluguel aluguel = new Aluguel();
        copyDTOToEntity(dto, aluguel);
        for(Livro l: aluguel.getLivros()){
            if(l.getQuantidade() > 0){
                l.setQuantidade(l.getQuantidade() -1);
            }
            else {
                l.setDisponivel(false);
            }
            livroService.update(mapper.map(l,LivroDTO.class));
        }
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
        aluguel.getLivros().clear();
        for (LivroDTO obj : dto.getLivros()) {
            Livro livro = livroService.findById(obj.getId());
            if (Boolean.TRUE.equals(livro.getDisponivel())) {
                aluguel.getLivros().add(livro);
            } else {
                throw new EntityNotAvailableException("A solicitação não pode prosseguir devido a indisponibilidade de alguma das entidades");
            }
        }
        aluguel.setDataAluguel(LocalDate.now());
        aluguel.setDataEstipulada(aluguel.getDataAluguel().plusWeeks(1));
        if (dto.getDataDevolucao() != null) {
            aluguel.setDataDevolucao(dto.getDataDevolucao());
            aluguel.setPendente(false);
        } else {
            aluguel.setPendente(true);
        }
        Usuario usuario = mapper.map(alunoService.findById(dto.getAlunoId()), Usuario.class);
        aluguel.setUsuario(usuario);
    }
}
