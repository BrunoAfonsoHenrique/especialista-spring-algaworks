package com.algaworks.locadora.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Filme {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private String diretor;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String duracao;

    @Column(nullable = false)
    private String classificacao;

}
