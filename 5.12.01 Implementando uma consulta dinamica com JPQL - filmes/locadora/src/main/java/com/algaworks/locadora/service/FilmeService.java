package com.algaworks.locadora.service;

import com.algaworks.locadora.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.locadora.domain.model.Filme;
import com.algaworks.locadora.domain.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmeRepository;

    public Filme salvarFilme(Filme filme) {
        Filme filmeSalvo = filmeRepository.salvar(filme);

        return filmeSalvo;

    }

    public void excluir(Long filmeId) {
        try {
            filmeRepository.remover(filmeId);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Filme de codigo %d não pode ser removido, " +
                    "pois esta em uso", filmeId));
        }
    }

}
