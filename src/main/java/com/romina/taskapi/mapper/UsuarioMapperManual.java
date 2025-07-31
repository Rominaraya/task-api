package com.romina.taskapi.mapper;

import com.romina.taskapi.dto.UsuarioRequestDto;
import com.romina.taskapi.dto.UsuarioResponseDto;
import com.romina.taskapi.entities.Usuario;

public class UsuarioMapperManual {

    public static Usuario toEntity(UsuarioRequestDto dto){
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre().trim());
        usuario.setEmail(dto.getEmail().toLowerCase());
        usuario.setPassword(dto.getPassword());
        return usuario;
    }

    public  static UsuarioResponseDto toDto ( Usuario usuario){
        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}
