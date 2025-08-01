package com.romina.taskapi.controller;

import com.romina.taskapi.dto.SubtareaRequestDto;
import com.romina.taskapi.dto.SubtareaResponseDto;
import com.romina.taskapi.services.SubtareaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea/{tareaId}/subtareas")
public class SubtareaController {
    private  final SubtareaService subtareaService;

    public SubtareaController(SubtareaService subtareaService) {
        this.subtareaService = subtareaService;
    }

    @PostMapping
    public ResponseEntity<SubtareaResponseDto> crearSubtarea(
            @PathVariable Long tareaId,
            @Valid @RequestBody SubtareaRequestDto dto){
        SubtareaResponseDto creada = subtareaService.crearSubtarea(tareaId, dto);
        return  ResponseEntity.ok(creada);
    }
     @GetMapping
    public ResponseEntity<List<SubtareaResponseDto>> ObtenerSubtareas(@PathVariable Long tareaId){
        List<SubtareaResponseDto> subtareas = subtareaService.obtenerSubtareasPorTarea(tareaId);
        return ResponseEntity.ok(subtareas);
     }

}
