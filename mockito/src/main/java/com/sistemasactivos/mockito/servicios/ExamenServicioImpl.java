package com.sistemasactivos.mockito.servicios;

import com.sistemasactivos.mockito.modelos.Examen;
import com.sistemasactivos.mockito.repositorios.ExamenRepositorio;

import java.util.Optional;

public class ExamenServicioImpl implements ExamenServicio {
    private ExamenRepositorio repositorio;

    public ExamenServicioImpl(ExamenRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Examen findExamenPorNombre(String nombre) {
        Optional<Examen> examenOptional = repositorio.findAll()
                .stream()
                .filter(examen -> examen.getNombre().equals(nombre))
                .findFirst();

        Examen examen = null;
        if (examenOptional.isPresent())
            examen = examenOptional.orElseThrow();

        return examen;
    }
}
