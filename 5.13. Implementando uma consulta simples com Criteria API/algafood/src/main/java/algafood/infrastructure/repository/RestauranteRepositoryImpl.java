package algafood.infrastructure.repository;

import algafood.domain.model.Restaurante;
import algafood.domain.respository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        // CriteriaQuery é uma interface por montar um estrutura de uma Query
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);

        criteria.from(Restaurante.class); // from Restaurante


        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList();

    }
}
