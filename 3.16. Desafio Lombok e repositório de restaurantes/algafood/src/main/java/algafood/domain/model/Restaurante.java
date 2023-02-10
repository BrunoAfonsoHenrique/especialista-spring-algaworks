package algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;


}
