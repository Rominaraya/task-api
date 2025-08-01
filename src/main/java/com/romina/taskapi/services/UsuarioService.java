package com.romina.taskapi.services;

import com.romina.taskapi.dto.UsuarioRequestDto;
import com.romina.taskapi.dto.UsuarioResponseDto;
import com.romina.taskapi.entities.Usuario;
import com.romina.taskapi.mapper.UsuarioMapperManual;
import com.romina.taskapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para crear un nuevo usuario
    public UsuarioResponseDto crearUsuario(UsuarioRequestDto dto) {
        Usuario usuario = UsuarioMapperManual.toEntity(dto);
        Usuario guardado = usuarioRepository.save(usuario);
        return UsuarioMapperManual.toDto(guardado);
    }

    // Método para obtener todos los usuarios
    public List<UsuarioResponseDto> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapperManual::toDto)
                .collect(Collectors.toList());
    }
}