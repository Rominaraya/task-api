package com.romina.taskapi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descripcion;
    private LocalDate fechaLimite;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL)
    private List<Subtarea> subtareas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tarea_etiqueta",
            joinColumns = @JoinColumn(name = "tarea_id"),
            inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
    private Set<Etiqueta> etiquetas = new HashSet<>();

    public Tarea() {
    }

    public Tarea(String titulo, String descripcion, LocalDate fechaLimite, EstadoTarea estado, Usuario usuario, List<Subtarea> subtareas, Set<Etiqueta> etiquetas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.usuario = usuario;
        this.subtareas = subtareas;
        this.etiquetas = etiquetas;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Subtarea> getSubtareas() {
        return subtareas;
    }

    public void setSubtareas(List<Subtarea> subtareas) {
        this.subtareas = subtareas;
    }

    public Set<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
