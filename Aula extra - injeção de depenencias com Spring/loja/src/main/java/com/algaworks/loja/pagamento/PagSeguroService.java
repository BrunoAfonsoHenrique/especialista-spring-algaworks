package com.algaworks.loja.pagamento;

import java.math.BigDecimal;

public class PagSeguroService {

    private String token;

    public PagSeguroService(String token) {
        this.token = token;
    }

    public void efetuarPagamento(String numeroCartao, BigDecimal valor) {
        System.out.printf("[PAGSEGURO] Usando token: %s \n", token);
        System.out.printf("[PAGSEGURO] Cobrando %f do cartão %s...\n", valor, numeroCartao);

//        throw new RuntimeException("Erro - Cobrando com PagSeguro");
    }
}
