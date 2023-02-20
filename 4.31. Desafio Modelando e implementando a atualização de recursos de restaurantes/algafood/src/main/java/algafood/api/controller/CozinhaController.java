package algafood.api.controller;

import algafood.domain.exceptions.EntidadeEmUsoException;
import algafood.domain.exceptions.EntidadeNaoEncontradaException;
import algafood.domain.model.Cozinha;
import algafood.domain.respository.CozinhaRepository;
import algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    CozinhaRepository cozinhaRepository;

    @Autowired
    CadastroCozinhaService cadastroCozinhaService;

    @GetMapping()
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }

          return ResponseEntity.notFound().build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }


    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {

        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);

        if(cozinhaAtual != null) {

            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id"); // Mesma coisa que o método de cima + prático

            cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtual);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> delete(@PathVariable Long cozinhaId) {
        try {
            cadastroCozinhaService.excluir(cozinhaId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
        catch(EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch(EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
