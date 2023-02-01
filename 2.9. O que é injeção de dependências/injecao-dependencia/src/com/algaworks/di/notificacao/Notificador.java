package com.algaworks.di.notificacao;

import com.algaworks.di.modelo.Cliente;

public interface Notificador {

    public void notificar(Cliente cliente, String mensagem);
}
