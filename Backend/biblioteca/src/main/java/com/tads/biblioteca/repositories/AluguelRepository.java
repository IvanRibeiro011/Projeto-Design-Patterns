package com.tads.biblioteca.repositories;

import com.tads.biblioteca.entities.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

    
}
