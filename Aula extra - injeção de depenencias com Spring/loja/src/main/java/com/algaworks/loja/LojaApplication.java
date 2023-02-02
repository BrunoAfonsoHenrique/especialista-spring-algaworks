package com.algaworks.loja;

import com.algaworks.loja.venda.Venda;
import com.algaworks.loja.venda.VendaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class LojaApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(LojaApplication.class, args);

		VendaService vendaService = applicationContext.getBean(VendaService.class);

		Venda venda = new Venda();
		venda.setProduto("Camiseta basica");
		venda.setQuantidade(2);
		venda.setPrecoUnitario(new BigDecimal(109.5));

		vendaService.registrar(venda, "42568894586587");
	}

}
