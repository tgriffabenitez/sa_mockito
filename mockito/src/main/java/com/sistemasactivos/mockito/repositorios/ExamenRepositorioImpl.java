package com.sistemasactivos.mockito.repositorios;

import com.sistemasactivos.mockito.modelos.Examen;

import java.util.Arrays;
import java.util.List;

public class ExamenRepositorioImpl implements ExamenRepositorio {

    @Override
    public List<Examen> findAll() {
        return Arrays.asList(
                new Examen(5L, "Matemáticas"),
                new Examen(6L, "Lenguaje"),
                new Examen(7L, "Historia")
        );
    }
}
