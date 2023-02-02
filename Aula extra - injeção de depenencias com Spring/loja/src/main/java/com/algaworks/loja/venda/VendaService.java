package com.algaworks.loja.venda;

import com.algaworks.loja.pagamento.PagSeguroService;

import java.math.BigDecimal;

public class VendaService {

    public void registrar(Venda venda, String numeroCartao) {

        BigDecimal valorTotal = venda.getPrecoUnitario().multiply(new BigDecimal(venda.getQuantidade()));

        System.out.printf("[VENDA] Registrando venda de %s no valor total de %f... \n",
                venda.getProduto(), valorTotal);

        PagSeguroService pagSeguroService = new PagSeguroService("98654189478951498985");
        pagSeguroService.efetuarPagamento(numeroCartao, valorTotal);
    }
}
