package algafood.api.controller;

import algafood.domain.model.Restaurante;
import algafood.domain.respository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    RestauranteRepository restauranteRepository;

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

}
