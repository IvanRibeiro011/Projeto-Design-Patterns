package com.tads.biblioteca.repositories;

import com.tads.biblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Long> {
//    @Query("SELECT obj from Livro obj where exists (obj.disponivel)")
//    Boolean livroDisponivelByNome(String nome);

    Boolean existsByNome(String nome);
}
