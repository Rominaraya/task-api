package com.romina.taskapi.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "subtareas")
public class Subtarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTarea estado;

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tarea tarea;

    public Subtarea() {
    }

    public Subtarea(String titulo, EstadoTarea estado, Tarea tarea) {
        this.titulo = titulo;
        this.estado = estado;
        this.tarea = tarea;
    }

    public Long getId() {
        return id;
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

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
}
