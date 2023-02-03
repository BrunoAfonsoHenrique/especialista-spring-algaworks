package algafoodapi.notificacao;

import algafoodapi.modelo.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
