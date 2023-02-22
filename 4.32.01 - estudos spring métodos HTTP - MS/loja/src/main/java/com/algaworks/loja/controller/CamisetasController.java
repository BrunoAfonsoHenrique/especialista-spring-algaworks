package com.algaworks.loja.controller;

import com.algaworks.loja.Exceptions.CamisetaNaoEncontradaException;
import com.algaworks.loja.domain.model.Camisetas;
import com.algaworks.loja.repository.CamisetaRepository;
import com.algaworks.loja.service.CadastroCamisetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/camisetas")
public class CamisetasController {

    @Autowired
    CadastroCamisetaService cadastroCamisetaService;

    @Autowired
    CamisetaRepository camisetaRepository;

    @GetMapping
    public ResponseEntity<?> listarTodasCamisetas() {
        try {
            List<Camisetas> camiseta = cadastroCamisetaService.listarCamisetas();
            return ResponseEntity.status(HttpStatus.OK).body(camiseta);
        } catch (CamisetaNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{camisetaId}")
    public ResponseEntity<?> buscaId(@PathVariable Long camisetaId) {
        try {
            Camisetas camisetas = cadastroCamisetaService.buscarCamisetaPorId(camisetaId);
            return ResponseEntity.status(HttpStatus.OK).body(camisetas);
        } catch (CamisetaNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Camisetas> postar(@RequestBody Camisetas camiseta) {
        Camisetas camisetaNova = cadastroCamisetaService.salvarNovaCamiseta(camiseta);

        return ResponseEntity.status(HttpStatus.CREATED).body(camisetaNova);
    }

    @PutMapping("/{camisetaId}")
    public ResponseEntity<Camisetas> atualizarCamiseta(@PathVariable Long camisetaId,
                                                       @RequestBody Camisetas camiseta) {

        try {
            Camisetas camisetaAtual = cadastroCamisetaService.atualizarCamiseta(camisetaId, camiseta);
            return ResponseEntity.status(HttpStatus.OK).body(camisetaAtual);
        } catch (CamisetaNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
