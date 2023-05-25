package com.sistemasactivos.mockito.repositorios;

import java.util.List;

public interface PreguntaRepositorio {
    List<String> findPreguntaPorExamenId(Long id);
}
