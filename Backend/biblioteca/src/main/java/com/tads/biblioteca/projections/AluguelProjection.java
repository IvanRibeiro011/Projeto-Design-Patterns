package com.tads.biblioteca.projections;

import java.time.LocalDate;

public interface AluguelProjection {
    Long getId();
    Long getAlunoId();
    LocalDate getDataAluguel();
    LocalDate getDataDevolucao();
    LocalDate getDataEstipulada();
    Long getLivroId();

}
