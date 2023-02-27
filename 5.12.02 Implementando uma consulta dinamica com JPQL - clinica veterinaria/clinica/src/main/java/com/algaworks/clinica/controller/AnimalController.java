package com.algaworks.clinica.controller;

import com.algaworks.clinica.domain.model.AnimalDomestico;
import com.algaworks.clinica.domain.repository.AnimalRepository;
import com.algaworks.clinica.exceptions.AnimalDomesticoNaoEncontradoException;
import com.algaworks.clinica.service.AnimalDomesticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animaldomestico")
public class AnimalController {

    @Autowired
    AnimalDomesticoService animalDomesticoService;

    @GetMapping("listartodos")
    public ResponseEntity<?> listarTodosAnimaisDomesticos() {
        List<AnimalDomestico> animalDomesticos = animalDomesticoService.listarTodosAnimaisDomesticos();

        return ResponseEntity.status(HttpStatus.OK).body(animalDomesticos);
    }

    @GetMapping("/buscarPorId/{animalDomesticoId}")
    public ResponseEntity<?> bucarPorId(@PathVariable Long animalDomesticoId) {
        try {
            Optional<AnimalDomestico> animal = animalDomesticoService
                    .buscarAnimalDomesticoPorId(animalDomesticoId);

            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } catch (AnimalDomesticoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



}
