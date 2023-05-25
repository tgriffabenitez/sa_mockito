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
    public Optional<Examen> findExamenPorNombre(String nombre) {
        return repositorio.findAll()
                .stream()
                .filter(examen -> examen.getNombre().equals(nombre))
                .findFirst();
    }
}
