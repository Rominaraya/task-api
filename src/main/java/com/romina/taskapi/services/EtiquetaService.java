package com.romina.taskapi.services;

import com.romina.taskapi.dto.EtiquetaRequestDto;
import com.romina.taskapi.dto.EtiquetaResponseDto;
import com.romina.taskapi.entities.Etiqueta;
import com.romina.taskapi.mapper.EtiquetaMapperManual;
import com.romina.taskapi.repository.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;

    public EtiquetaService(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }

    public EtiquetaResponseDto crearEtiqueta(EtiquetaRequestDto dto){
        Etiqueta etiqueta = EtiquetaMapperManual.toEntity(dto);
        Etiqueta guardada = etiquetaRepository.save(etiqueta);
        return EtiquetaMapperManual.toDto(guardada);
    }

    public List<EtiquetaResponseDto> ObtenerTodos(){
        return  etiquetaRepository.findAll().stream()
                .map(EtiquetaMapperManual::toDto)
                .collect(Collectors.toList());
    }

}
