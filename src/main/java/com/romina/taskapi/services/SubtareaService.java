package com.romina.taskapi.services;


import com.romina.taskapi.dto.SubtareaRequestDto;
import com.romina.taskapi.dto.SubtareaResponseDto;
import com.romina.taskapi.entities.Subtarea;
import com.romina.taskapi.entities.Tarea;
import com.romina.taskapi.repository.SubtareaRepository;
import com.romina.taskapi.repository.TareaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtareaService {
    private final SubtareaRepository subtareaRepository;
    private final TareaRepository tareaRepository;

    public SubtareaService(SubtareaRepository subtareaRepository, TareaRepository tareaRepository) {
        this.subtareaRepository = subtareaRepository;
        this.tareaRepository = tareaRepository;
    }

    public SubtareaResponseDto crearSubtarea(Long tareaId, SubtareaRequestDto dto){
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(()-> new EntityNotFoundException("Tarea no encontrada"));
                Subtarea subtarea = new Subtarea();
                subtarea.setTitulo(dto.getTitulo());
                subtarea.setEstado(dto.getEstado());
                subtarea.setTarea(tarea);

                Subtarea guardada = subtareaRepository.save(subtarea);
               return new SubtareaResponseDto(guardada.getId(), guardada.getTitulo(), guardada.getEstado());
    }

    public List<SubtareaResponseDto> obtenerSubtareasPorTarea(Long tareaId){
        List<Subtarea> subtareas = subtareaRepository.findByTareaId(tareaId);
        return subtareas.stream()
                .map(s-> new SubtareaResponseDto(s.getId(),s.getTitulo(),s.getEstado()))
                .toList();
    }

}
