package algafoodapi.notificacao;

import algafoodapi.modelo.Cliente;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class NotificadorSms implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através por SMS através do telefone %s\n",
                cliente.getNome(), cliente.getTelefone() ,mensagem);
    }



}
