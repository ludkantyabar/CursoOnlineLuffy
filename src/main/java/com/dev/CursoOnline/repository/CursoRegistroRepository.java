package com.dev.CursoOnline.repository;

import com.dev.CursoOnline.model.CursoRegistro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRegistroRepository extends JpaRepository<CursoRegistro, Long> {
    List<CursoRegistro> findByUsuarioId(Long usuarioId);
}