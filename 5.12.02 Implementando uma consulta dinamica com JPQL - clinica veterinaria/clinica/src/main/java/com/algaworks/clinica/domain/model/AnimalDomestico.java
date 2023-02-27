package com.algaworks.clinica.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AnimalDomestico {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String classificacao;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String genero;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CuidadorAnimal cuidador;



}
