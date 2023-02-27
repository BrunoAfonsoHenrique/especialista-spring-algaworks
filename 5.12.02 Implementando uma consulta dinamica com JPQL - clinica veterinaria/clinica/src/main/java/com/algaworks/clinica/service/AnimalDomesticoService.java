package com.algaworks.clinica.service;

import com.algaworks.clinica.domain.model.AnimalDomestico;
import com.algaworks.clinica.domain.repository.AnimalRepository;
import com.algaworks.clinica.exceptions.AnimalDomesticoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalDomesticoService {

    @Autowired
    AnimalRepository animalRepository;

    public List<AnimalDomestico> listarTodosAnimaisDomesticos() {
        return animalRepository.findAll();

    }

    public Optional<AnimalDomestico> buscarAnimalDomesticoPorId(Long id) throws AnimalDomesticoNaoEncontradoException {
        Optional<AnimalDomestico> animal = animalRepository.findById(id);

        if (animal.isEmpty()){
            throw new AnimalDomesticoNaoEncontradoException("ANIMAL DOMESTICO NAO LOCALIZADO NA BASE DE DADOS");
        }
        return animal;
    }
}
