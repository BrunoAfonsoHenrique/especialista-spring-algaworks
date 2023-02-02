package algafoodapi;

import algafoodapi.notificacao.Notificador;
import algafoodapi.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgaConfig {

    @Bean
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificador = new NotificadorEmail("smtp.gmail.com.br");
        notificador.setCaixaAlta(true);

        return notificador;
    }

}
