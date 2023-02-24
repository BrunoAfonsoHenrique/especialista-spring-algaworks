package com.algaworks.locadora.domain.implementation;

import com.algaworks.locadora.domain.model.Filme;
import com.algaworks.locadora.domain.repository.FilmeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FilmeRepositoryImpl implements FilmeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Filme> listar() {
        return manager.createQuery("from Filme", Filme.class)
                .getResultList();
    }

    @Override
    public Filme buscar(Long id) {
        return manager.find(Filme.class, id);
    }

    @Override
    @Transactional
    public Filme salvar(Filme filme) {
        return manager.merge(filme);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Filme filme = buscar(id);

        if(filme == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(filme);
    }
}
