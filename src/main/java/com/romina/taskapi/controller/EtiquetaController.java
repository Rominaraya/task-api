package com.romina.taskapi.controller;


import com.romina.taskapi.dto.EtiquetaRequestDto;
import com.romina.taskapi.dto.EtiquetaResponseDto;
import com.romina.taskapi.services.EtiquetaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {
  private final EtiquetaService etiquetaService;

    public EtiquetaController(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    @PostMapping
    public ResponseEntity<EtiquetaResponseDto> crearEtiqueta (@Valid @RequestBody EtiquetaRequestDto dto){
        EtiquetaResponseDto creado = etiquetaService.crearEtiqueta(dto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public  ResponseEntity<List<EtiquetaResponseDto>> obtenerTodos(){
        List<EtiquetaResponseDto> etiquetas = etiquetaService.ObtenerTodos();
        return ResponseEntity.ok(etiquetas);
    }
}
