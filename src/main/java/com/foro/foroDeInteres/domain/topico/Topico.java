package com.foro.foroDeInteres.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    private Boolean status;
    private String autor;
    private String curso;


    public Topico(DatosRegistroTopico datos) {
        this.status = true;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fecha_creacion = LocalDateTime.now();
        this.autor = datos.autor();
        this.curso = datos.curso();
    }

    public void actualizarTopico(DatosActualizarTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.autor() != null) {
            this.autor = datos.autor();
        }
        if (datos.curso() != null) {
            this.curso = datos.curso();
        }
    }

    public void eliminar() {
        this.status = false;
    }

}
