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
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    CozinhaRepository cozinhaRepository;

    @Autowired
    CadastroCozinhaService cadastroCozinhaService;

    @GetMapping()
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

        if (cozinha.isPresent()) {
            return ResponseEntity.ok(cozinha.get());
        }

          return ResponseEntity.notFound().build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }


    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
                                             @RequestBody Cozinha cozinha) {

        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

        if(cozinhaAtual.isPresent()) {

            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id"); // Mesma coisa que o método de cima + prático

            Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
            return ResponseEntity.ok(cozinhaSalva);
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
