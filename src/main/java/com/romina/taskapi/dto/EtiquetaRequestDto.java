package com.romina.taskapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EtiquetaRequestDto {
    @NotBlank
    @Size(max = 100)
    private String nombre;

    public EtiquetaRequestDto() {
    }

    public EtiquetaRequestDto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
