package com.sistemasactivos.mockito.servicios;

import com.sistemasactivos.mockito.modelos.Examen;
import com.sistemasactivos.mockito.repositorios.ExamenRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamenServicioImplTest {

    ExamenServicio servicio;
    ExamenRepositorio repositorio;

    @BeforeEach
    void setUp() {
        this.repositorio = mock(ExamenRepositorio.class); // indico el nombre de la clase o interfaz que quiero mockear
        this.servicio = new ExamenServicioImpl(repositorio);
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
        List<Examen> datos = Arrays.asList(
                new Examen(5L, "Matemáticas"),
                new Examen(6L, "Lenguaje"),
                new Examen(7L, "Historia")
        );

        // Esto se lee: cuando el metodo findAll() de la clase ExamenRepositorio se llame, entonces devolver la lista de examenes.
        when(repositorio.findAll()).thenReturn(datos);

        Optional<Examen> examen = servicio.findExamenPorNombre("Matemáticas");

        assertTrue(examen.isPresent());
        assertEquals(5L, examen.orElseThrow().getId());
        assertEquals("Matemáticas", examen.orElseThrow().getNombre());

    }

    @Test
    void findExamenPorNombreListaVacia() {
        List<Examen> datos = List.of();

        when(repositorio.findAll()).thenReturn(datos);

        Optional<Examen> examen = servicio.findExamenPorNombre("Matemáticas");

        assertFalse(examen.isPresent());
    }
}