package com.romina.taskapi.mapper;

import com.romina.taskapi.dto.SubtareaRequestDto;
import com.romina.taskapi.dto.SubtareaResponseDto;
import com.romina.taskapi.entities.Subtarea;

public class SubtareaMapperManual {
    public static Subtarea toEntity(SubtareaRequestDto dto){
        Subtarea subTarea = new Subtarea();
        subTarea.setTitulo(dto.getTitulo().trim());
        subTarea.setEstado(dto.getEstado());
        return subTarea;
    }

    public static SubtareaResponseDto toDto (Subtarea subTarea){
        SubtareaResponseDto dto = new SubtareaResponseDto();
        dto.setId(subTarea.getId());
        dto.setTitulo(subTarea.getTitulo());
        dto.setEstado(subTarea.getEstado());
        return dto;
    }
}
