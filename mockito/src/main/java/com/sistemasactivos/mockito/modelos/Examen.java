package com.sistemasactivos.mockito.modelos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Examen {
    private Long id;
    private String nombre;
    private List<String> preguntas = new ArrayList<>();

    public Examen(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
