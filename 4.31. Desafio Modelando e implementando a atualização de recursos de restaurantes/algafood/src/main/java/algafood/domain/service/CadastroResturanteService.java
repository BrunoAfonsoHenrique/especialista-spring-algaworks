package algafood.domain.service;

import algafood.domain.exceptions.EntidadeNaoEncontradaException;
import algafood.domain.model.Cozinha;
import algafood.domain.model.Restaurante;
import algafood.domain.respository.CozinhaRepository;
import algafood.domain.respository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroResturanteService {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }


}
