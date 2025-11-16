package com.dev.CursoOnline.dto;

public class CursoRegistroDTO {

    private Long cursoId;
    private Long usuarioId;

    // Getters y setters
    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}