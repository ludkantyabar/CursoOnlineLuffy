package com.dev.CursoOnline.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "curso_registro")
public class CursoRegistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "El usuario es obligatorio")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    @NotNull(message = "El curso es obligatorio")
    private Curso curso;

    // Métodos para obtener los IDs
    public Long getCursoId() {
        return curso != null ? curso.getId() : null;
    }

    public Long getUsuarioId() {
        return usuario != null ? usuario.getId() : null;
    }

    // Método de validación
    public void validar() {
        if (curso == null || usuario == null) {
            throw new IllegalArgumentException("Curso ID y Usuario ID son obligatorios");
        }
    }

    // Getters y setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }
}