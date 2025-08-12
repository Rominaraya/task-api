package com.romina.taskapi.services;

import com.romina.taskapi.dto.UsuarioRequestDto;
import com.romina.taskapi.dto.UsuarioResponseDto;
import com.romina.taskapi.entities.Usuario;
import com.romina.taskapi.mapper.UsuarioMapperManual;
import com.romina.taskapi.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UsuarioResponseDto crearUsuario(UsuarioRequestDto dto) {
        Usuario usuario = UsuarioMapperManual.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        Usuario guardado = usuarioRepository.save(usuario);
        return UsuarioMapperManual.toDto(guardado);
    }

    public List<UsuarioResponseDto> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioResponseDto(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getEmail()
                ))
                .toList();
    }
}