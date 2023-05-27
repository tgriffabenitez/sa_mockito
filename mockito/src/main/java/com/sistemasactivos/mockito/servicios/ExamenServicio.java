package com.sistemasactivos.mockito.servicios;

import com.sistemasactivos.mockito.modelos.Examen;

import java.util.Optional;

public interface ExamenServicio {
    Optional<Examen> findExamenPorNombre(String nombre);
    Examen findExamenPorNombreConPreguntas(String nombre);
    Examen guardar(Examen examen);
}
