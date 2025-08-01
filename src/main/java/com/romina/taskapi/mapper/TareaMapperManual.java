package com.romina.taskapi.mapper;

import com.romina.taskapi.dto.TareaRequestDto;
import com.romina.taskapi.dto.TareaResponseDto;
import com.romina.taskapi.entities.Tarea;

public class TareaMapperManual {

    public static Tarea toEntity(TareaRequestDto dto){
        Tarea tarea = new Tarea();
        tarea.setTitulo(dto.getTitulo().trim());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setFechaLimite(dto.getFechaLimite());
        tarea.setEstado(dto.getEstado());
        return tarea;
    }

    public static TareaResponseDto toDto (Tarea tarea){
        TareaResponseDto dto = new TareaResponseDto();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setFechaLimite(tarea.getFechaLimite());
        dto.setEstado(tarea.getEstado());
        return dto;
    }

}
