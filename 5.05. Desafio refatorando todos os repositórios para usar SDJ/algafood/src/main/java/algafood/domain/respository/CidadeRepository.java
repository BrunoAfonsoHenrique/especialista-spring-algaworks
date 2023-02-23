package algafood.domain.respository;

import algafood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
