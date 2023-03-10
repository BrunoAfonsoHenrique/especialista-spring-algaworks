package algafood.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable //uma classe que é incorporavel, tem a capacidade de ser incorporada em uma Entidade. Ela é a parte de uma entidade, e não uma entidade em si.
public class Endereco {

    @Column(name = "endereco_cep")
    private String cep;

    @Column(name = "endereco_logradouro")
    private String logradouro;

    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_bairro")
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_cidade_id")
    private Cidade cidade;


}
