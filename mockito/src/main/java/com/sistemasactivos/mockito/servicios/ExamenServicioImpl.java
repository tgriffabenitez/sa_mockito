package com.sistemasactivos.mockito.servicios;

import com.sistemasactivos.mockito.modelos.Examen;
import com.sistemasactivos.mockito.repositorios.ExamenRepositorio;
import com.sistemasactivos.mockito.repositorios.PreguntaRepositorio;

import java.util.List;
import java.util.Optional;

public class ExamenServicioImpl implements ExamenServicio {
    private ExamenRepositorio examenRepositorio;
    private PreguntaRepositorio preguntaRepositorio;

    public ExamenServicioImpl(ExamenRepositorio repositorio, PreguntaRepositorio preguntaRepositorio) {
        this.examenRepositorio = repositorio;
        this.preguntaRepositorio = preguntaRepositorio;
    }

    @Override
    public Optional<Examen> findExamenPorNombre(String nombre) {
        return examenRepositorio.findAll()
                .stream()
                .filter(examen -> examen.getNombre().equals(nombre))
                .findFirst();
    }

    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre) {
        Optional<Examen> examenOptional = findExamenPorNombre(nombre);
        Examen examen = null;
        if (examenOptional.isPresent()){
            examen = examenOptional.orElseThrow();
            List<String> preguntas = preguntaRepositorio.findPreguntaPorExamenId(examen.getId());
            examen.setPreguntas(preguntas);
        }
        return examen;
    }
}
