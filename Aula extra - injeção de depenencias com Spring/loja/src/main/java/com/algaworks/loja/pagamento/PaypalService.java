package com.algaworks.loja.pagamento;

import java.math.BigDecimal;

public class PaypalService implements GatawayPagamento {

    private String usuario;
    private String senha;

    public PaypalService(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public void efetuarPagamento(String numeroCartao, BigDecimal valor) {
        System.out.printf("[PAYPAL] Usando usuario: %s \n", this.usuario);
        System.out.printf("[PAYPAL] Cobrando %f do cartão %s...\n", valor, numeroCartao);

//        throw new RuntimeException("Erro - Cobrando com PagSeguro");
    }
}
