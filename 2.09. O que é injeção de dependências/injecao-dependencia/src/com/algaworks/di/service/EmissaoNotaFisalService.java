package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.modelo.Produto;
import com.algaworks.di.notificacao.NotificadorEmail;

public class EmissaoNotaFisalService {

    public void emitir(Cliente cliente, Produto produto) {
        // TODO emite nota fiscal aqui

        NotificadorEmail notificador = new NotificadorEmail();
        notificador.notificar(cliente, "Nota fiscal do produto " +
                produto.getNome() + " foi emitida!");
    }
}
