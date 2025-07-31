package com.romina.taskapi.mapper;

import com.romina.taskapi.dto.EtiquetaRequestDto;
import com.romina.taskapi.dto.EtiquetaResponseDto;
import com.romina.taskapi.entities.Etiqueta;

public class EtiquetaMapperManual {
    public static Etiqueta toEntity (EtiquetaRequestDto dto){
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNombre(dto.getNombre().trim());
        return etiqueta;
    }

    public static EtiquetaResponseDto toDto (Etiqueta etiqueta){
        EtiquetaResponseDto dto = new EtiquetaResponseDto();
        dto.setId(etiqueta.getId());
        dto.setNombre(etiqueta.getNombre());
        return dto;
    }

}
