package com.tads.biblioteca.repositories;

import com.tads.biblioteca.entities.Aluguel;
import com.tads.biblioteca.projections.AluguelProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    @Query(nativeQuery = true, value =
            "SELECT tb_aluguel.id , tb_aluno.id as alunoId , tb_aluguel.data_aluguel" +
                    ", tb_aluguel.data_devolucao, tb_aluguel.data_estipulada" +
                    ",tb_aluguel_livro as livroId tb_ FROM TB_ALUGUEL join tb_aluno " +
                    "on aluno.id = tb_aluguel.aluno_id " +
                    "join tb_aluguel_livro " +
                    "on tb_aluguel_livro.aluguel_id = tb_aluguel.id " +
                    "where tb_aluno.nome = :name")
    List<AluguelProjection> findAluguelByAluno(String name);
}
