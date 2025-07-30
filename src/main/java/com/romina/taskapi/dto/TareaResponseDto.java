package com.romina.taskapi.dto;

import com.romina.taskapi.entities.EstadoTarea;
import java.time.LocalDate;

public class TareaResponseDto {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaLimite;
    private EstadoTarea estado;

    public TareaResponseDto() {
    }

    public TareaResponseDto(Long id, String titulo, String descripcion, LocalDate fechaLimite, EstadoTarea estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
