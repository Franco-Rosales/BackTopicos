package com.foro.foroDeInteres.domain.topico;

public record DatosListaTopicos(Long id,String titulo, String mensaje, String autor, String curso) {

    public DatosListaTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso());
    }

}
