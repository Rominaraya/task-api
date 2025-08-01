package com.romina.taskapi.services;

import com.romina.taskapi.dto.TareaRequestDto;
import com.romina.taskapi.dto.TareaResponseDto;
import com.romina.taskapi.entities.Etiqueta;
import com.romina.taskapi.entities.Tarea;
import com.romina.taskapi.entities.Usuario;
import com.romina.taskapi.mapper.TareaMapperManual;
import com.romina.taskapi.repository.EtiquetaRepository;
import com.romina.taskapi.repository.TareaRepository;
import com.romina.taskapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EtiquetaRepository etiquetaRepository;

    public TareaService(TareaRepository tareaRepository, UsuarioRepository usuarioRepository, EtiquetaRepository etiquetaRepository) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
        this.etiquetaRepository = etiquetaRepository;
    }

    public TareaResponseDto crearTarea(Long usuarioId, TareaRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Tarea tarea = TareaMapperManual.toEntity(dto);
        tarea.setUsuario(usuario);

        Tarea guardada = tareaRepository.save(tarea);
        return TareaMapperManual.toDto(guardada);
    }

    public void asignarEtiqueta(Long tareaId, Long etiquetaId) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada"));
        Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId)
                .orElseThrow(() -> new EntityNotFoundException("Etiqueta no encontrada"));
        tarea.getEtiquetas().add(etiqueta);
        tareaRepository.save(tarea);

    }

    public List<TareaResponseDto> obtenerTareasPorUsuario(Long usuarioId) {
        List<Tarea> tareas = tareaRepository.findByUsuarioId(usuarioId);
        return tareas.stream()
                .map(TareaMapperManual::toDto)
                .toList();
    }
}
