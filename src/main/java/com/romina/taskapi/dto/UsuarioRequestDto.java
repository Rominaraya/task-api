package com.romina.taskapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 60, message = "Máximo 60 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 30, message = "Debe tener entre 6 y 30 caracteres")
    private String password;

    public UsuarioRequestDto() {
    }

    public UsuarioRequestDto(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
