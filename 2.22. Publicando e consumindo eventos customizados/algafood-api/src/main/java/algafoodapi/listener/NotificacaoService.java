package algafoodapi.listener;

import algafoodapi.notificacao.NivelUrgencia;
import algafoodapi.notificacao.Notificador;
import algafoodapi.notificacao.TipoDoNotificador;
import algafoodapi.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "seu cadastro no sistema esta ativo");
    }
}
