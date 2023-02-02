package com.algaworks.algafoodapi.service;

import com.algaworks.algafoodapi.modelo.Cliente;
import com.algaworks.algafoodapi.notificacao.NotificadorEmail;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    private NotificadorEmail notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
    }
}
