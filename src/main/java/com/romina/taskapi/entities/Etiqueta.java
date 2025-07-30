package com.romina.taskapi.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "etiquetas")
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "etiquetas")
    private Set<Tarea> tareas = new HashSet<>();

    public Etiqueta() {
    }

    public Etiqueta(Long id, String nombre, Set<Tarea> tareas) {
        this.id = id;
        this.nombre = nombre;
        this.tareas = tareas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tarea> tareas) {
        this.tareas = tareas;
    }
}
