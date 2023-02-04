package algafoodapi.service;

import algafoodapi.modelo.Cliente;
import algafoodapi.notificacao.NivelUrgencia;
import algafoodapi.notificacao.Notificador;
import algafoodapi.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class AtivacaoClienteService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

//    @PostConstruct
    public void init() {
        System.out.println("INIT");
    }

//    @PreDestroy
    public void destroy() {
        System.out.println("DESTROY");
    }


    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");


    }
}
