package com.vinicius.os.controllers;

import com.vinicius.os.DTO.TecnicoFullDTO;
import com.vinicius.os.DTO.TecnicoLiteDTO;
import com.vinicius.os.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    TecnicoService tecnicoService;

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoLiteDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(tecnicoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoLiteDTO>> findAll(){
        return ResponseEntity.ok().body(tecnicoService.findAll());
    }

    @PostMapping()
    public ResponseEntity<TecnicoLiteDTO> createTecnico(@RequestBody @Valid TecnicoFullDTO tecnico){
        TecnicoLiteDTO tec = tecnicoService.createTecnico(tecnico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tec.getId()).toUri();
        return ResponseEntity.created(uri).body(tec);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoLiteDTO> updateTecnico(@PathVariable Integer id, @RequestBody @Valid TecnicoFullDTO tecnicoDTO){
        return ResponseEntity.ok().body(tecnicoService.updateTecnico(id, tecnicoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTecnico(@PathVariable Integer id){
        tecnicoService.deleteTecnico(id);
        return ResponseEntity.noContent().build();
    }
}
