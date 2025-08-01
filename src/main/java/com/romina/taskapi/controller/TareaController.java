package com.romina.taskapi.controller;

import com.romina.taskapi.dto.TareaRequestDto;
import com.romina.taskapi.dto.TareaResponseDto;
import com.romina.taskapi.services.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/{usuarioId}/tareas")
public class TareaController {
    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping
    public ResponseEntity<TareaResponseDto> crearTarea(
            @PathVariable Long usuarioId,
            @Valid @RequestBody TareaRequestDto dto) {
        TareaResponseDto creada = tareaService.crearTarea(usuarioId, dto);
        return ResponseEntity.ok(creada);
    }

    @GetMapping
    public ResponseEntity<List<TareaResponseDto>> obtenerTareas(@PathVariable Long usuarioId) {
        List<TareaResponseDto> tareas = tareaService.obtenerTareasPorUsuario(usuarioId);
        return ResponseEntity.ok(tareas);
    }

}
