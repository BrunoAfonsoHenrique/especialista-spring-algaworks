package algafood.domain.repository;

import algafood.domain.model.Cozinha;
import algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Cozinha> listar();

    Cozinha buscar(Long id);

    Cozinha salvar(Restaurante restaurante);

    void remover(Restaurante restaurante);
}
