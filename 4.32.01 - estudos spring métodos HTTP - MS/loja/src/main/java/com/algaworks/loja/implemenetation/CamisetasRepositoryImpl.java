package com.algaworks.loja.implemenetation;

import com.algaworks.loja.domain.model.Camisetas;
import com.algaworks.loja.repository.CamisetaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CamisetasRepositoryImpl implements CamisetaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Camisetas> listar() {
        return manager.createQuery("from Camisetas", Camisetas.class)
                .getResultList();
    }

    @Override
    public Camisetas buscar(Long id) {
        return manager.find(Camisetas.class, id);
    }

    @Override
    @Transactional
    public Camisetas salvar(Camisetas camisetas) {
        return manager.merge(camisetas);
    }

    @Override
    public void remover(Long id) {
        Camisetas camisetas = buscar(id);

        if(camisetas == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(camisetas);
    }
}
