package com.sistemasactivos.mockito;

import com.sistemasactivos.mockito.modelos.Examen;

import java.util.Arrays;
import java.util.List;

public class Datos {
    public final static List<Examen> EXAMENES = Arrays.asList(
            new Examen(5L, "Matemáticas"),
            new Examen(6L, "Lenguaje"),
            new Examen(7L, "Historia")
    );

    public final static List<String> PREGUNTAS = Arrays.asList(
            "Aritmetica",
            "Integrales",
            "Derivadas",
            "Trigonometria",
            "Geometria"
    );

    /*
     * Dejo el id en null ya que quiero testearlo de forma autoincremental
     */
    public final static Examen EXAMEN = new Examen(null, "Física");
}
