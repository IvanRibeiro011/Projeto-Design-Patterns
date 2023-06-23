package com.tads.biblioteca.repositories;

import com.tads.biblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Long> {
    Boolean existsByNome(String nome);
}
