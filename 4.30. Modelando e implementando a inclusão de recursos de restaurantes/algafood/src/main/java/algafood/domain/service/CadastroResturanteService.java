package algafood.domain.service;

import algafood.domain.model.Restaurante;
import algafood.domain.respository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CadastroResturanteService {

    @Autowired
    RestauranteRepository restauranteRepository;

    public Restaurante salvar(Restaurante restaurante) {
        return restauranteRepository.salvar(restaurante);
    }


}
