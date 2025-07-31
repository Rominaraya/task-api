package com.romina.taskapi.repository;

import com.romina.taskapi.entities.Subtarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubtareaRepository extends JpaRepository<Subtarea , Long > {
    List<Subtarea> findByTareaId(Long tareaId);
}
