package com.algaworks.loja;

import com.algaworks.loja.pagamento.GatawayPagamento;
import com.algaworks.loja.pagamento.PagSeguroService;
import com.algaworks.loja.pagamento.PaypalService;
import com.algaworks.loja.venda.VendaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration // indica que que essa classe tem como objetivo declarar os beans através de métodos
public class LojaConfig {

    @Bean // indica que este método produz/intancia um bean que vai ser gerenciado pelo container do spring
    public PagSeguroService pagSeguroService() {
        return new PagSeguroService("98654189478951498985");
    }

    @Primary
    @Bean // indica que este método produz/intancia um bean que vai ser gerenciado pelo container do spring
    public PaypalService paypalService() {
        return new PaypalService("algaworks", "123");
    }

//    @Bean
//    public VendaService vendaService(GatawayPagamento gatawayPagamento) {
//        return new VendaService(gatawayPagamento);
//    }
}
