package com.sistemasactivos.mockito.servicios;

import com.sistemasactivos.mockito.modelos.Examen;

public interface ExamenServicio {
    Examen findExamenPorNombre(String nombre);
}
