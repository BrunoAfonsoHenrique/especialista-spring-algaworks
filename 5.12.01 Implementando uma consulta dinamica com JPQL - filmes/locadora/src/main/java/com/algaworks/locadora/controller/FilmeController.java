package com.algaworks.locadora.controller;

import com.algaworks.locadora.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.locadora.domain.model.Filme;
import com.algaworks.locadora.domain.repository.FilmeRepository;
import com.algaworks.locadora.service.FilmeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    FilmeService filmeService;

    @GetMapping
    public List<Filme> listar() {
        return filmeRepository.listar();
    }

    @GetMapping("/{filmeId}")
    public ResponseEntity<Filme> buscarFilmePorId(@PathVariable Long filmeId) {
        Filme filme = filmeRepository.buscar(filmeId);

        if(filme != null) {
            return ResponseEntity.status(HttpStatus.OK).body(filme);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Filme adicionarNovoFilme(@RequestBody Filme filme) {

        return filmeService.salvarFilme(filme);
    }

    @PutMapping("/{filmeId}")
    public ResponseEntity<Filme> atualizar(@PathVariable Long filmeId,
                                            @RequestBody Filme filme) {

        Filme filmeAtual = filmeRepository.buscar(filmeId);

        if(filmeAtual != null) {
            BeanUtils.copyProperties(filme, filmeAtual, "id");

            filmeAtual = filmeRepository.salvar(filmeAtual);

            return ResponseEntity.status(HttpStatus.OK).body(filmeAtual);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{filmeId}")
    public ResponseEntity<Filme> delete(@PathVariable Long filmeId) {
        try {
            filmeService.excluir(filmeId);

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
