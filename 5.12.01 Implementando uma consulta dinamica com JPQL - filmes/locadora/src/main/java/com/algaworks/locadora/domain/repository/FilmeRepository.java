package com.algaworks.locadora.domain.repository;


import com.algaworks.locadora.domain.model.Filme;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FilmeRepository {

    List<Filme> listar();

    Filme buscar(Long id);

    Filme salvar(Filme filme);

    void remover(Long id);
}
