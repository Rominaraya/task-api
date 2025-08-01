package com.romina.taskapi.dto;

import com.romina.taskapi.entities.EstadoTarea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SubtareaRequestDto {

    @NotBlank
    @Size(max = 100)
    private String titulo;

    @NotNull(message = "El estado no puede ser nulo")
    private EstadoTarea estado;

    public SubtareaRequestDto() {
    }

    public SubtareaRequestDto(String titulo, EstadoTarea estado) {
        this.titulo = titulo;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }
}
