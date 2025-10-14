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

import com.dev.CursoOnline.model.CursoRegistro;
import com.dev.CursoOnline.service.CursoRegistroService;

@RestController
@RequestMapping("/cursoregistros")
public class CursoRegistroController {

    @Autowired
    private CursoRegistroService cursoRegistroService;

    @PostMapping
    public CursoRegistro crearCursoRegistro(@RequestBody CursoRegistro cursoRegistro) {
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
    public void eliminarCursoRegistro(@PathVariable Long id) {
        cursoRegistroService.eliminarCursoRegistro(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<CursoRegistro> obtenerRegistrosPorUsuario(@PathVariable("usuarioId") Long usuarioId) {
        return cursoRegistroService.buscarPorUsuarioId(usuarioId);
    }

}