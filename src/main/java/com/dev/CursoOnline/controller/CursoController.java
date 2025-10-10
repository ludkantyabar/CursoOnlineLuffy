package com.dev.CursoOnline.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.CursoOnline.model.Curso;
import com.dev.CursoOnline.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public Curso crearCurso(@RequestBody Curso curso) {
        return cursoService.guardarCurso(curso);
    }

    @GetMapping("/{id}")
    public Optional<Curso> obtenerCurso(@PathVariable Long id) {
        return cursoService.obtenerCursoPorId(id);
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

   
    @DeleteMapping("/{id}")
    public void eliminarCurso(@PathVariable("id") Long id) {
        cursoService.eliminarCurso(id);
    }
}
