package com.tads.biblioteca.strategy;

import com.tads.biblioteca.strategy.interfaces.CalculadorDeMultas;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CalculadorPadrao implements CalculadorDeMultas {
    @Value("${application_tax_padrao}")
    private Double taxa;
    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * taxa;
    }
}
