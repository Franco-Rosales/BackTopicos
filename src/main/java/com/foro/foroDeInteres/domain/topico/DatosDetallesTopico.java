package com.foro.foroDeInteres.domain.topico;

public record DatosDetallesTopico(Long id, String titulo, String mensaje, String autor, String curso) {

    public DatosDetallesTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso());
    }
}
