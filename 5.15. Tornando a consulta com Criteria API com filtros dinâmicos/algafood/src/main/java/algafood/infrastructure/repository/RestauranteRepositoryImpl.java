package algafood.infrastructure.repository;

import algafood.domain.model.Restaurante;
import algafood.domain.respository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
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

        Root<Restaurante> root = criteria.from(Restaurante.class); // from Restaurante

        ArrayList<Predicate> predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (taxaFreteInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }

        if (taxaFreteFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }


        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList();

    }
}
