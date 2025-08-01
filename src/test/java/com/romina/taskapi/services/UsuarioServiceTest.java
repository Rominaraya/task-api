package com.romina.taskapi.services;

import com.romina.taskapi.dto.UsuarioRequestDto;
import com.romina.taskapi.dto.UsuarioResponseDto;
import com.romina.taskapi.entities.Usuario;
import com.romina.taskapi.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void crearUsuario_deberiaGuardarYRetornarDto() {
        // Arrange
        UsuarioRequestDto requestDto = new UsuarioRequestDto();
        requestDto.setNombre("Romina");
        requestDto.setEmail("romina@example.com");

        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNombre("Romina");
        usuarioEntity.setEmail("romina@example.com");

        Usuario usuarioGuardado = new Usuario();
        usuarioGuardado.setId(1L);
        usuarioGuardado.setNombre("Romina");
        usuarioGuardado.setEmail("romina@example.com");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        UsuarioResponseDto resultado = usuarioService.crearUsuario(requestDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Romina", resultado.getNombre());
        assertEquals("romina@example.com", resultado.getEmail());

        verify(usuarioRepository).save(any(Usuario.class));
    }
    @Test
    void obtenerTodos_deberiaRetornarListaDeDtos() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNombre("Romina");
        usuario1.setEmail("romina@example.com");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNombre("Carlos");
        usuario2.setEmail("carlos@example.com");

        List<Usuario> usuarios = List.of(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioResponseDto> resultado = usuarioService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        assertEquals("Romina", resultado.get(0).getNombre());
        assertEquals("romina@example.com", resultado.get(0).getEmail());

        assertEquals("Carlos", resultado.get(1).getNombre());
        assertEquals("carlos@example.com", resultado.get(1).getEmail());

        verify(usuarioRepository).findAll();
    }

}