package algafood.api.controller;

import algafood.domain.exceptions.EntidadeNaoEncontradaException;
import algafood.domain.model.Cozinha;
import algafood.domain.model.Restaurante;
import algafood.domain.respository.CozinhaRepository;
import algafood.domain.respository.RestauranteRepository;
import algafood.domain.service.CadastroResturanteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
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

        merge(campos, restauranteAtual);

        return atualizar(restauranteAtual, restauranteId);

    }

    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        System.out.println(restauranteOrigem);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            System.out.println(nomePropriedade + " = " + valorPropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

}
