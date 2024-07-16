package com.foro.foroDeInteres.controller;


import com.foro.foroDeInteres.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        var topico = new Topico(datos);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesTopico(topico));

    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopicos>> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacion){
        var page = topicoRepository.findAllByStatusTrue(paginacion).map(DatosListaTopicos::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarTopico datos){
        var topico = topicoRepository.getReferenceById(datos.id());
        topico.actualizarTopico(datos);
        return ResponseEntity.ok(new DatosDetallesTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        topico.eliminar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallesTopico> obtener(@PathVariable Long id){
        var topico = topicoRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new DatosDetallesTopico(topico));
    }


}
