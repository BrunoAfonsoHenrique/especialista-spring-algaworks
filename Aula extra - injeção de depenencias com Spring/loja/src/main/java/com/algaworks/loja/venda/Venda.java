package com.algaworks.loja.venda;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Venda {

    private BigDecimal precoUnitario;
    private int quantidade;
    private String produto;

}
