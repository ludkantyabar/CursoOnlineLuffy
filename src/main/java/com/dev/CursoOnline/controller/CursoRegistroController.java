package com.dev.CursoOnline.controller;

import com.dev.CursoOnline.model.CursoRegistro;
import com.dev.CursoOnline.service.CursoRegistroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursoregistros")
public class CursoRegistroController {

    @Autowired
    private CursoRegistroService cursoRegistroService;

    @PostMapping
    public CursoRegistro crearCursoRegistro(@Valid @RequestBody CursoRegistro cursoRegistro) {
        if (cursoRegistro.getCursoId() == null || cursoRegistro.getUsuarioId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Curso ID y Usuario ID son obligatorios");
        }
        return cursoRegistroService.guardarCursoRegistro(cursoRegistro);
    }

    @GetMapping("/{id}")
    public Optional<CursoRegistro> obtenerCursoRegistro(@PathVariable Long id) {
        return cursoRegistroService.obtenerCursoRegistroPorId(id);
    }

    @GetMapping
    public List<CursoRegistro> listarCursoRegistros() {
        return cursoRegistroService.listarCursoRegistros();
    }

    @DeleteMapping("/{id}")
    public void eliminarCursoRegistro(@PathVariable("id") Long id) {
        cursoRegistroService.eliminarCursoRegistro(id);
    }
}