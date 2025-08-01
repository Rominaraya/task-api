package com.romina.taskapi.mapper;

import com.romina.taskapi.dto.UsuarioRequestDto;
import com.romina.taskapi.dto.UsuarioResponseDto;
import com.romina.taskapi.entities.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperManualTest {

    @Test
    void toEntity_deberiaMapearCorrectamenteDesdeRequestDto() {
        // Arrange
        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setNombre("Romina");
        dto.setEmail("romina@example.com");

        Usuario entidad = UsuarioMapperManual.toEntity(dto);

        assertNotNull(entidad);
        assertEquals("Romina", entidad.getNombre());
        assertEquals("romina@example.com", entidad.getEmail());
    }

    @Test
    void toDto_deberiaMapearCorrectamenteDesdeEntidad() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Romina");
        usuario.setEmail("romina@example.com");

        UsuarioResponseDto dto = UsuarioMapperManual.toDto(usuario);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Romina", dto.getNombre());
        assertEquals("romina@example.com", dto.getEmail());
    }
}