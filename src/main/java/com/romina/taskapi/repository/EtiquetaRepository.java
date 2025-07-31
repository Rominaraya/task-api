package com.romina.taskapi.repository;

import com.romina.taskapi.entities.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
    Etiqueta findByNombre(String nombre);
}
