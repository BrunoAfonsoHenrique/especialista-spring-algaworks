package com.algaworks.loja.pagamento;

import java.math.BigDecimal;

public interface GatawayPagamento {

    public void efetuarPagamento(String numeroCartao, BigDecimal valor);

}
