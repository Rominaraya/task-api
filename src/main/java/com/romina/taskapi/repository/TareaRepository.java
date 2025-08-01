package com.romina.taskapi.repository;

import com.romina.taskapi.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TareaRepository  extends JpaRepository<Tarea, Long> {
    List<Tarea> findByUsuarioId(Long usuarioId);

}
