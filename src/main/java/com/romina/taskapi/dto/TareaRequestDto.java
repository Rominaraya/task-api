package com.romina.taskapi.dto;

import com.romina.taskapi.entities.EstadoTarea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TareaRequestDto {
    @NotBlank
    @Size(max = 100)
    private String titulo;

    @Size(max = 255)
    private String descripcion;

    @NotNull
    private LocalDate fechaLimite;

    @NotNull
    private EstadoTarea estado;

    public TareaRequestDto() {
    }

    public TareaRequestDto(String titulo, String descripcion, LocalDate fechaLimite, EstadoTarea estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }
}

