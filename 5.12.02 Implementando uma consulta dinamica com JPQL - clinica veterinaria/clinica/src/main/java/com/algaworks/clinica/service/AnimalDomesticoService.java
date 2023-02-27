package com.algaworks.clinica.service;

import com.algaworks.clinica.domain.model.AnimalDomestico;
import com.algaworks.clinica.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalDomesticoService {

    @Autowired
    AnimalRepository animalRepository;

    public List<AnimalDomestico> listarTodosAnimaisDomesticos() {
        return animalRepository.findAll();

    }
}
