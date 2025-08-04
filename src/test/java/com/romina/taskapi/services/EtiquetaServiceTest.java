package com.romina.taskapi.services;

import com.romina.taskapi.dto.EtiquetaRequestDto;
import com.romina.taskapi.dto.EtiquetaResponseDto;
import com.romina.taskapi.entities.Etiqueta;
import com.romina.taskapi.repository.EtiquetaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EtiquetaServiceTest {

    @Mock
    private EtiquetaRepository etiquetaRepository;

    @InjectMocks
    private EtiquetaService etiquetaService;

    @Test
    void crearEtiqueta_deberiaGuardarYRetornarDto() {

        EtiquetaRequestDto requestDto = new EtiquetaRequestDto("Backend");

        Etiqueta etiquetaEntity = new Etiqueta();
        etiquetaEntity.setNombre("Backend");

        Etiqueta etiquetaGuardada = new Etiqueta();
        etiquetaGuardada.setId(1L);
        etiquetaGuardada.setNombre("Backend");

        when(etiquetaRepository.save(any(Etiqueta.class))).thenReturn(etiquetaGuardada);

        EtiquetaResponseDto resultado = etiquetaService.crearEtiqueta(requestDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Backend", resultado.getNombre());

        verify(etiquetaRepository).save(any(Etiqueta.class));
    }

    @Test
    void obtenerTodos_deberiaRetornarListaDeDtos() {
        // Arrange
        Etiqueta e1 = new Etiqueta();
        e1.setId(1L);
        e1.setNombre("Java");

        Etiqueta e2 = new Etiqueta();
        e2.setId(2L);
        e2.setNombre("Spring");

        when(etiquetaRepository.findAll()).thenReturn(List.of(e1, e2));

        List<EtiquetaResponseDto> resultado = etiquetaService.ObtenerTodos();

        assertEquals(2, resultado.size());
        assertEquals("Java", resultado.get(0).getNombre());
        assertEquals("Spring", resultado.get(1).getNombre());

        verify(etiquetaRepository).findAll();
    }
}