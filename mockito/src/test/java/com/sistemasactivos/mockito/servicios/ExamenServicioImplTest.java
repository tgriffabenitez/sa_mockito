package com.sistemasactivos.mockito.servicios;

import com.sistemasactivos.mockito.Datos;
import com.sistemasactivos.mockito.modelos.Examen;
import com.sistemasactivos.mockito.repositorios.ExamenRepositorio;
import com.sistemasactivos.mockito.repositorios.PreguntaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Con esto habilito las anotaciones de mockito
class ExamenServicioImplTest {

    @Mock // esto es para que se cree un mock de la clase o interfaz que se le indique
    ExamenRepositorio examenRepositorio;

    @Mock // esto es para que se cree un mock de la clase o interfaz que se le indique
    PreguntaRepositorio preguntaRepositorio;

    @InjectMocks // esto es para que se inyecten los mocks en la clase que se le indique
    ExamenServicioImpl examenServicio;

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

    @Test
    void testPreguntaExamenVerificar() {
        when(examenRepositorio.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepositorio.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);

        Examen examen = examenServicio.findExamenPorNombreConPreguntas("Matemáticas");

        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Aritmetica"));

        // verifico que el metodo findAll() de la clase ExamenRepositorio se haya llamado al menos una vez
        verify(examenRepositorio).findAll();

        // verifico que el metodo findPreguntaPorExamenId() de la clase PreguntaRepositorio se haya llamado al menos una vez
        verify(preguntaRepositorio).findPreguntaPorExamenId(anyLong());
    }

    @Test
    void testNoExisteExamenVerify() {
        when(examenRepositorio.findAll()).thenReturn(Collections.emptyList());
        when(preguntaRepositorio.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);

        Examen examen = examenServicio.findExamenPorNombreConPreguntas("Matemáticas");
        assertNull(examen);

        // verifico que el metodo findAll() de la clase ExamenRepositorio se haya llamado al menos una vez
        verify(examenRepositorio).findAll();

        // verifico que el metodo findPreguntaPorExamenId() de la clase PreguntaRepositorio se haya llamado al menos una vez
        verify(preguntaRepositorio).findPreguntaPorExamenId(anyLong());
    }

    @Test
    void testGuardarExamen() {
        Examen newExamen = Datos.EXAMEN;
        newExamen.setPreguntas(Datos.PREGUNTAS);


        when(examenRepositorio.guardar(any(Examen.class))).thenReturn(Datos.EXAMEN);
        Examen examen = examenServicio.guardar(newExamen);

        assertNotNull(examen.getId());
        assertEquals(8L, examen.getId());
        assertEquals("Física", examen.getNombre());
        verify(examenRepositorio).guardar(any(Examen.class));
        verify(preguntaRepositorio).guardarVarias(anyList());
    }
}