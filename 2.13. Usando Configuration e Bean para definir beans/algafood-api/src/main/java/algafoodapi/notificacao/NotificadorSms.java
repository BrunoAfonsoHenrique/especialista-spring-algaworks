package algafoodapi.notificacao;

import algafoodapi.modelo.Cliente;
import org.springframework.stereotype.Component;

//@Component
public class NotificadorSms implements Notificador{


    public NotificadorSms() {
        System.out.println("NotificadorSMS");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s por SMS através do telefone%s: %s\n",
                cliente.getNome(), cliente.getTelefone(), mensagem);
    }
}
