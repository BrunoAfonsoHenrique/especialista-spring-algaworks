package algafood.api.controller;

import algafood.domain.exceptions.EntidadeNaoEncontradaException;
import algafood.domain.model.Cozinha;
import algafood.domain.model.Restaurante;
import algafood.domain.respository.CozinhaRepository;
import algafood.domain.respository.RestauranteRepository;
import algafood.domain.service.CadastroResturanteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CadastroResturanteService cadastroResturanteService;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {

        Restaurante restaurante = restauranteRepository.buscar(restauranteId);

        if(restaurante != null) {
            return ResponseEntity.status(HttpStatus.OK).body(restaurante);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroResturanteService.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        }
        catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@RequestBody Restaurante restaurante,
                                     @PathVariable Long restauranteId) {

        try {
            Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

            if(restauranteAtual != null) {

                BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

                restauranteAtual = cadastroResturanteService.salvar(restauranteAtual);
                return ResponseEntity.status(HttpStatus.OK).body(restauranteAtual);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcialmente(@PathVariable Long restauranteId,
                                                   @RequestBody Map<String, Object> campos) {

        Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

        if (restauranteAtual == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            System.out.println(nomePropriedade + " = " + valorPropriedade);
        });

        return atualizar(restauranteAtual, restauranteId);

    }

}
