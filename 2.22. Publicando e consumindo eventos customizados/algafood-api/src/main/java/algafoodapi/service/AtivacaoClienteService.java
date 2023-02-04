package algafoodapi.service;

import algafoodapi.modelo.Cliente;
import algafoodapi.notificacao.NivelUrgencia;
import algafoodapi.notificacao.Notificador;
import algafoodapi.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        // dizer para o container que o cliente esta ativo no momento
        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
    }
}
