package com.algaworks.loja.service;

import com.algaworks.loja.Exceptions.CamisetaNaoEncontradaException;
import com.algaworks.loja.Exceptions.EntidadeEmUsoException;
import com.algaworks.loja.Exceptions.EntidadeNaoEncontradaException;
import com.algaworks.loja.domain.model.Camisetas;
import com.algaworks.loja.repository.CamisetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCamisetaService {

    @Autowired
    CamisetaRepository camisetaRepository;

    public List<Camisetas> listarCamisetas() throws CamisetaNaoEncontradaException {
        List<Camisetas> camiseta = camisetaRepository.listar();

        if(camiseta.isEmpty()) {
            throw new CamisetaNaoEncontradaException("Camiseta não encontrada");
        }
        return camiseta;
    }

    public Camisetas buscarCamisetaPorId(Long camisetaId) throws CamisetaNaoEncontradaException {
        Camisetas camiseta = camisetaRepository.buscar(camisetaId);

        if(camiseta == null) {
            throw new CamisetaNaoEncontradaException("Camiseta não encontrada");
        }

        return camiseta;
    }

    public Camisetas salvarNovaCamiseta(Camisetas camiseta) {
        return camisetaRepository.salvar(camiseta);
    }


    public Camisetas atualizarCamiseta(Long camisetaId, Camisetas camiseta) throws CamisetaNaoEncontradaException {

        Camisetas camisetaAtual = camisetaRepository.buscar(camisetaId);

            if(camisetaAtual != null) {

                BeanUtils.copyProperties(camiseta, camisetaAtual, "id");


                camisetaAtual = camisetaRepository.salvar(camisetaAtual);

                return camisetaAtual;
            }

            throw new CamisetaNaoEncontradaException("Camiseta não encontrada");


    }

    public void excluir(Long camisetaId) {

        try {
            camisetaRepository.remover(camisetaId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de camiseta com codigo %d",
                    camisetaId));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Camiseta de codigo %d não pode ser removida, " +
                    "pois esta em uso", camisetaId));
        }
    }

}
