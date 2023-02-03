package algafoodapi.service;

import algafoodapi.modelo.Cliente;
import algafoodapi.notificacao.NivelUrgencia;
import algafoodapi.notificacao.Notificador;
import algafoodapi.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    //@Qualifier("urgente")
    @TipoDoNotificador(NivelUrgencia.URGENTE)
    @Autowired
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");


    }


}
