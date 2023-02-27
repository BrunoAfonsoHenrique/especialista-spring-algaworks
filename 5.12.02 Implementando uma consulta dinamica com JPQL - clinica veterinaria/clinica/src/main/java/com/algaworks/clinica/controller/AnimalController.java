package com.algaworks.clinica.controller;

import com.algaworks.clinica.domain.model.AnimalDomestico;
import com.algaworks.clinica.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animaldomestico")
public class AnimalController {

    @Autowired
    AnimalRepository animalRepository;

    @GetMapping
    public List<AnimalDomestico> listaTodosAnimaisDomesticos() {
        return animalRepository.findAll();
    }


}
