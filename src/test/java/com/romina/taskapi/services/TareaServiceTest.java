package com.romina.taskapi.services;

import com.romina.taskapi.dto.TareaRequestDto;
import com.romina.taskapi.dto.TareaResponseDto;
import com.romina.taskapi.entities.EstadoTarea;
import com.romina.taskapi.entities.Etiqueta;
import com.romina.taskapi.entities.Tarea;
import com.romina.taskapi.entities.Usuario;
import com.romina.taskapi.repository.EtiquetaRepository;
import com.romina.taskapi.repository.TareaRepository;
import com.romina.taskapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EtiquetaRepository etiquetaRepository;

    @InjectMocks
    private TareaService tareaService;

    @Test
    void crearTarea_deberiaGuardarYRetornarDto() {

        Long usuarioId = 1L;

        TareaRequestDto requestDto = new TareaRequestDto();
        requestDto.setTitulo("Estudiar Spring Boot");
        requestDto.setDescripcion("Repasar relaciones JPA");
        requestDto.setFechaLimite(LocalDate.of(2025, 8, 10));
        requestDto.setEstado(EstadoTarea.PENDIENTE);

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setNombre("Romina");
        usuario.setEmail("romina@example.com");

        Tarea tareaEntity = new Tarea();
        tareaEntity.setTitulo("Estudiar Spring Boot");
        tareaEntity.setDescripcion("Repasar relaciones JPA");
        tareaEntity.setFechaLimite(LocalDate.of(2025, 8, 10));
        tareaEntity.setEstado(EstadoTarea.PENDIENTE);
        tareaEntity.setUsuario(usuario);

        Tarea tareaGuardada = new Tarea();
        tareaGuardada.setId(100L);
        tareaGuardada.setTitulo("Estudiar Spring Boot");
        tareaGuardada.setDescripcion("Repasar relaciones JPA");
        tareaGuardada.setFechaLimite(LocalDate.of(2025, 8, 10));
        tareaGuardada.setEstado(EstadoTarea.PENDIENTE);
        tareaGuardada.setUsuario(usuario);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tareaGuardada);


        TareaResponseDto resultado = tareaService.crearTarea(usuarioId, requestDto);

        assertNotNull(resultado);
        assertEquals(100L, resultado.getId());
        assertEquals("Estudiar Spring Boot", resultado.getTitulo());
        assertEquals("Repasar relaciones JPA", resultado.getDescripcion());
        assertEquals(LocalDate.of(2025, 8, 10), resultado.getFechaLimite());
        assertEquals(EstadoTarea.PENDIENTE, resultado.getEstado());

        verify(usuarioRepository).findById(usuarioId);
        verify(tareaRepository).save(any(Tarea.class));
    }

    @Test
    void obtenerTareasPorUsuario_deberiaRetornarListaDeDtos() {

        Long usuarioId = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setNombre("Romina");

        Tarea tarea1 = new Tarea();
        tarea1.setId(1L);
        tarea1.setTitulo("Estudiar JPA");
        tarea1.setDescripcion("Relaciones entre entidades");
        tarea1.setFechaLimite(LocalDate.of(2025, 8, 15));
        tarea1.setEstado(EstadoTarea.PENDIENTE);
        tarea1.setUsuario(usuario);

        Tarea tarea2 = new Tarea();
        tarea2.setId(2L);
        tarea2.setTitulo("Practicar DTOs");
        tarea2.setDescripcion("Request y Response");
        tarea2.setFechaLimite(LocalDate.of(2025, 8, 20));
        tarea2.setEstado(EstadoTarea.EN_PROGRESO);
        tarea2.setUsuario(usuario);

        when(tareaRepository.findByUsuarioId(usuarioId)).thenReturn(List.of(tarea1, tarea2));

        List<TareaResponseDto> resultado = tareaService.obtenerTareasPorUsuario(usuarioId);


        assertEquals(2, resultado.size());
        assertEquals("Estudiar JPA", resultado.get(0).getTitulo());
        assertEquals("Practicar DTOs", resultado.get(1).getTitulo());

        verify(tareaRepository).findByUsuarioId(usuarioId);
    }

    @Test
    void crearTarea_usuarioNoEncontrado_deberiaLanzarExcepcion() {

        Long usuarioId = 99L;

        TareaRequestDto requestDto = new TareaRequestDto();
        requestDto.setTitulo("Tarea fantasma");
        requestDto.setDescripcion("No debería guardarse");
        requestDto.setFechaLimite(LocalDate.of(2025, 8, 30));
        requestDto.setEstado(EstadoTarea.PENDIENTE);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            tareaService.crearTarea(usuarioId, requestDto);
        });

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(usuarioRepository).findById(usuarioId);
        verifyNoInteractions(tareaRepository);
    }

    @Test
    void asignarEtiqueta_deberiaAsignarEtiquetaATarea() {

        Long tareaId = 1L;
        Long etiquetaId = 10L;

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Romina");

        Tarea tarea = new Tarea();
        tarea.setId(tareaId);
        tarea.setTitulo("Aprender MapStruct");
        tarea.setUsuario(usuario);
        tarea.setEtiquetas(new HashSet<>());

        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setId(etiquetaId);
        etiqueta.setNombre("Java");

        when(tareaRepository.findById(tareaId)).thenReturn(Optional.of(tarea));
        when(etiquetaRepository.findById(etiquetaId)).thenReturn(Optional.of(etiqueta));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);


        tareaService.asignarEtiqueta(tareaId, etiquetaId);


        assertTrue(tarea.getEtiquetas().contains(etiqueta));
        verify(tareaRepository).findById(tareaId);
        verify(etiquetaRepository).findById(etiquetaId);
        verify(tareaRepository).save(tarea);
    }
    @Test
    void asignarEtiqueta_tareaNoEncontrada_deberiaLanzarExcepcion() {

        Long tareaId = 999L;
        Long etiquetaId = 10L;

        when(tareaRepository.findById(tareaId)).thenReturn(Optional.empty());


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            tareaService.asignarEtiqueta(tareaId, etiquetaId);
        });

        assertEquals("Tarea no encontrada", exception.getMessage());
        verify(tareaRepository).findById(tareaId);
        verifyNoInteractions(etiquetaRepository);
    }
    @Test
    void asignarEtiqueta_etiquetaNoEncontrada_deberiaLanzarExcepcion() {
        Long tareaId = 1L;
        Long etiquetaId = 999L;

        Tarea tarea = new Tarea();
        tarea.setId(tareaId);
        tarea.setTitulo("Aprender Spring Boot");
        tarea.setEtiquetas(new HashSet<>());

        when(tareaRepository.findById(tareaId)).thenReturn(Optional.of(tarea));
        when(etiquetaRepository.findById(etiquetaId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            tareaService.asignarEtiqueta(tareaId, etiquetaId);
        });

        assertEquals("Etiqueta no encontrada", exception.getMessage());
        verify(tareaRepository).findById(tareaId);
        verify(etiquetaRepository).findById(etiquetaId);
    }


}