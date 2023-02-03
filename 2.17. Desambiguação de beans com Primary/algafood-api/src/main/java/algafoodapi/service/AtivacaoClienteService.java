package algafoodapi.service;

import algafoodapi.modelo.Cliente;
import algafoodapi.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {

    @Autowired
    private List<Notificador> notificadores;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        for(Notificador notificador : notificadores) {
            notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
        }

    }


}
