package algafoodapi.service;

import algafoodapi.modelo.Cliente;
import algafoodapi.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Autowired
    private Notificador notificador;

//    @Autowired Ponto de injecao (ideal)
//    public AtivacaoClienteService(Notificador notificador) {
//        this.notificador = notificador;
//    }

//    public AtivacaoClienteService(String qualquerCoisa) {
//
//    }

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
    }

//    @Autowired  Ponto de injecao
//    public void setNotificador(Notificador notificador) {
//        this.notificador = notificador;
//    }
}
