package com.sistemasactivos.mockito.servicios;

import com.sistemasactivos.mockito.Datos;
import com.sistemasactivos.mockito.modelos.Examen;
import com.sistemasactivos.mockito.repositorios.ExamenRepositorio;
import com.sistemasactivos.mockito.repositorios.PreguntaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamenServicioImplTest {

    ExamenServicio examenServicio;
    ExamenRepositorio examenRepositorio;
    PreguntaRepositorio preguntaRepositorio;

    @BeforeEach
    void setUp() {
        this.examenRepositorio = mock(ExamenRepositorio.class); // indico el nombre de la clase o interfaz que quiero mockear
        this.preguntaRepositorio = mock(PreguntaRepositorio.class);
        this.examenServicio = new ExamenServicioImpl(examenRepositorio, preguntaRepositorio);
    }

    @Test
    void findExamenPorNombre() {
        /*
         * Aca implemento Mockito, para que el test sea escalable. En vez de harcodear los posibles returns del metodo
         * findAll() en la clase ExamenRepositorioImpl, le digo a Mockito que me devuelva una lista de examenes
         * hardcodeados. Esto quiere decir que este test NO depende de la implementacion de ExamenRepositorioImpl, sino
         * que depende de Mockito.
         *
         * Solo se le pueden hacer mocks a los metodos que son publicos y no son estaticos.
         */

         // Esto se lee: cuando el metodo findAll() de la clase ExamenRepositorio se llame, entonces devolver la lista de examenes.
        when(examenRepositorio.findAll()).thenReturn(Datos.EXAMENES);

        Optional<Examen> examen = examenServicio.findExamenPorNombre("Matemáticas");

        assertTrue(examen.isPresent());
        assertEquals(5L, examen.orElseThrow().getId());
        assertEquals("Matemáticas", examen.orElseThrow().getNombre());

    }

    @Test
    void findExamenPorNombreListaVacia() {
        List<Examen> datos = List.of();

        when(examenRepositorio.findAll()).thenReturn(datos);

        Optional<Examen> examen = examenServicio.findExamenPorNombre("Matemáticas");

        assertFalse(examen.isPresent());
    }

    @Test
    void testPreguntaExamen() {
        when(examenRepositorio.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepositorio.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);

        Examen examen = examenServicio.findExamenPorNombreConPreguntas("Matemáticas");

        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Aritmetica"));
    }
}