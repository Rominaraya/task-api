package com.romina.taskapi.auth;

import com.romina.taskapi.entities.Usuario;
import com.romina.taskapi.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        Usuario nuevo = new Usuario();
        nuevo.setEmail(request.getEmail());
        nuevo.setNombre("Romina"); // o lo que quieras
        nuevo.setPassword(passwordEncoder.encode(request.getPassword()));
        usuarioRepository.save(nuevo);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }


}
