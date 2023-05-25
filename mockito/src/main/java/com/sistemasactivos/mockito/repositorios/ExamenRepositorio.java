package com.sistemasactivos.mockito.repositorios;

import com.sistemasactivos.mockito.modelos.Examen;

import java.util.List;

public interface ExamenRepositorio {
    List<Examen> findAll();
}
