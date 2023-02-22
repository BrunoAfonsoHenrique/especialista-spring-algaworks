package com.algaworks.loja.repository;

import com.algaworks.loja.domain.model.Camisetas;

import java.util.List;

public interface CamisetaRepository {

    List<Camisetas> listar();

    Camisetas buscar(Long id);

    Camisetas salvar(Camisetas camisetas);

    void remover(Long id);
}
