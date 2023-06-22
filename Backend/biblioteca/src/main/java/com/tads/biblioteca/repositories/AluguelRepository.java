package com.tads.biblioteca.repositories;

import com.tads.biblioteca.entities.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    @Query("select obj from Aluguel obj " +
            "join fetch obj.aluno " +
            "join fetch obj.livros" +
            " where obj.aluno.nome = :name")
    List<Aluguel> findAluguelByAluno(String name);
}
