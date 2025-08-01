package com.romina.taskapi.controller;

import com.romina.taskapi.dto.UsuarioRequestDto;
import com.romina.taskapi.dto.UsuarioResponseDto;
import com.romina.taskapi.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> crearUsuario (@Valid @RequestBody UsuarioRequestDto dto){
        UsuarioResponseDto creado = usuarioService.crearUsuario(dto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> obtenerTodos(){
        List<UsuarioResponseDto> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }
}
