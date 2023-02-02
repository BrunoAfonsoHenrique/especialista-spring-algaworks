package com.algaworks.loja.venda;

import com.algaworks.loja.pagamento.GatawayPagamento;
import com.algaworks.loja.pagamento.PagSeguroService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VendaService {

    private GatawayPagamento gatawayPagamento;

    public VendaService(GatawayPagamento gatawayPagamento) {
        this.gatawayPagamento = gatawayPagamento;
    }

    public void registrar(Venda venda, String numeroCartao) {

        BigDecimal valorTotal = venda.getPrecoUnitario().multiply(new BigDecimal(venda.getQuantidade()));

        System.out.printf("[VENDA] Registrando venda de %s no valor total de %f... \n",
                venda.getProduto(), valorTotal);

//      PagSeguroService pagSeguroService = new PagSeguroService("98654189478951498985"); auto acoplamento
        gatawayPagamento.efetuarPagamento(numeroCartao, valorTotal);
    }
}
