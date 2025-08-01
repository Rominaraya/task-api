package com.romina.taskapi.services;

import com.romina.taskapi.dto.SubtareaRequestDto;
import com.romina.taskapi.dto.SubtareaResponseDto;
import com.romina.taskapi.entities.EstadoTarea;
import com.romina.taskapi.entities.Subtarea;
import com.romina.taskapi.entities.Tarea;
import com.romina.taskapi.repository.SubtareaRepository;
import com.romina.taskapi.repository.TareaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubtareaServiceTest {

    private SubtareaRepository subtareaRepository;
    private TareaRepository tareaRepository;
    private SubtareaService subtareaService;

    @BeforeEach
    void setUp() {
        subtareaRepository = mock(SubtareaRepository.class);
        tareaRepository = mock(TareaRepository.class);
        subtareaService = new SubtareaService(subtareaRepository, tareaRepository);
    }

    @Test
    void crearSubtarea_deberiaCrearSubtareaCorrectamente() {
        Long tareaId = 1L;
        Tarea tarea = new Tarea();
        tarea.setId(tareaId);

        SubtareaRequestDto dto = new SubtareaRequestDto("Configurar MapStruct", EstadoTarea.PENDIENTE);
        Subtarea subtarea = new Subtarea(dto.getTitulo(), dto.getEstado(), tarea);
        subtarea.setId(100L);

        when(tareaRepository.findById(tareaId)).thenReturn(Optional.of(tarea));
        when(subtareaRepository.save(any(Subtarea.class))).thenReturn(subtarea);

        SubtareaResponseDto resultado = subtareaService.crearSubtarea(tareaId, dto);

        assertEquals(100L, resultado.getId());
        assertEquals("Configurar MapStruct", resultado.getTitulo());
        assertEquals(EstadoTarea.PENDIENTE, resultado.getEstado());

        verify(tareaRepository).findById(tareaId);
        verify(subtareaRepository).save(any(Subtarea.class));
    }

    @Test
    void crearSubtarea_tareaNoEncontrada_deberiaLanzarExcepcion() {
        Long tareaId = 999L;
        SubtareaRequestDto dto = new SubtareaRequestDto("Subtarea huérfana", EstadoTarea.PENDIENTE);

        when(tareaRepository.findById(tareaId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            subtareaService.crearSubtarea(tareaId, dto);
        });

        assertEquals("Tarea no encontrada", exception.getMessage());
        verify(tareaRepository).findById(tareaId);
        verifyNoInteractions(subtareaRepository);
    }

    @Test
    void obtenerSubtareasPorTarea_deberiaRetornarListaDeDtos() {
        Long tareaId = 1L;

        Subtarea s1 = new Subtarea("Diseñar DTO", EstadoTarea.PENDIENTE, null);
        s1.setId(1L);

        Subtarea s2 = new Subtarea("Validar campos", EstadoTarea.EN_PROGRESO, null);
        s2.setId(2L);

        when(subtareaRepository.findByTareaId(tareaId)).thenReturn(List.of(s1, s2));

        List<SubtareaResponseDto> resultado = subtareaService.obtenerSubtareasPorTarea(tareaId);

        assertEquals(2, resultado.size());
        assertEquals("Diseñar DTO", resultado.get(0).getTitulo());
        assertEquals("Validar campos", resultado.get(1).getTitulo());

        verify(subtareaRepository).findByTareaId(tareaId);
    }

    @Test
    void crearSubtarea_dtoInvalido_deberiaDetectarViolaciones() {
        SubtareaRequestDto dto = new SubtareaRequestDto("", null);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<jakarta.validation.ConstraintViolation<SubtareaRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size()); // título vacío + estado nulo
    }

    @Test
    void actualizarEstadoSubtarea_deberiaActualizarCorrectamente() {
        Long subtareaId = 5L;
        Subtarea subtarea = new Subtarea("Testear validación", EstadoTarea.PENDIENTE, null);
        subtarea.setId(subtareaId);

        when(subtareaRepository.findById(subtareaId)).thenReturn(Optional.of(subtarea));
        when(subtareaRepository.save(any(Subtarea.class))).thenReturn(subtarea);

        SubtareaResponseDto resultado = subtareaService.actualizarEstado(subtareaId, EstadoTarea.COMPLETADA);

        assertEquals(EstadoTarea.COMPLETADA, resultado.getEstado());
        verify(subtareaRepository).findById(subtareaId);
        verify(subtareaRepository).save(subtarea);
    }
}