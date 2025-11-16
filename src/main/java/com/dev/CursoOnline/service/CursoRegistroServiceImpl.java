package com.dev.CursoOnline.service;

import com.dev.CursoOnline.model.Curso;
import com.dev.CursoOnline.model.CursoRegistro;
import com.dev.CursoOnline.model.Usuario;
import com.dev.CursoOnline.repository.CursoRegistroRepository;
import com.dev.CursoOnline.repository.CursoRepository;
import com.dev.CursoOnline.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoRegistroServiceImpl implements CursoRegistroService {

    @Autowired
    private CursoRegistroRepository cursoRegistroRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public CursoRegistro guardarCursoRegistro(CursoRegistro cursoRegistro) {
        // Validar que cursoId y usuarioId no sean nulos
        if (cursoRegistro.getCursoId() == null) {
            throw new IllegalArgumentException("El ID del curso no puede ser nulo");
        }
        if (cursoRegistro.getUsuarioId() == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }

        // Validar y asociar el Curso
        Curso curso = cursoRepository.findById(cursoRegistro.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso con ID " + cursoRegistro.getCursoId() + " no encontrado"));

        // Validar y asociar el Usuario
        Usuario usuario = usuarioRepository.findById(cursoRegistro.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario con ID " + cursoRegistro.getUsuarioId() + " no encontrado"));

        // Asociar Curso y Usuario al registro
        cursoRegistro.setCurso(curso);
        cursoRegistro.setUsuario(usuario);

        // Guardar el registro
        return cursoRegistroRepository.save(cursoRegistro);
    }

    @Override
    public Optional<CursoRegistro> obtenerCursoRegistroPorId(Long id) {
        return cursoRegistroRepository.findById(id);
    }

    @Override
    public List<CursoRegistro> listarCursoRegistros() {
        return cursoRegistroRepository.findAll();
    }

    @Override
    public void eliminarCursoRegistro(Long id) {
        cursoRegistroRepository.deleteById(id);
    }

    @Override
    public List<CursoRegistro> buscarPorUsuarioId(Long usuarioId) {
        return cursoRegistroRepository.findByUsuarioId(usuarioId);
    }
}