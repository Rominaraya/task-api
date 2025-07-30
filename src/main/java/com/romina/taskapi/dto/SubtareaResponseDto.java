package com.romina.taskapi.dto;

import com.romina.taskapi.entities.EstadoTarea;

public class SubtareaResponseDto {
    private Long id;
    private String titulo;
    private EstadoTarea estado;

    public SubtareaResponseDto() {
    }

    public SubtareaResponseDto(Long id, String titulo, EstadoTarea estado) {
        this.id = id;
        this.titulo = titulo;
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

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }
}
