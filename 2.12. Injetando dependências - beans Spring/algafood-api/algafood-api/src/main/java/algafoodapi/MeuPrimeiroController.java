package algafoodapi;

import algafoodapi.modelo.Cliente;
import algafoodapi.service.AtivacaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;

        System.out.println("MeuPrimeiroController: " + ativacaoClienteService);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {

        Cliente joao = new Cliente("Joao", "joao@gmail.com", "11933335555");
        ativacaoClienteService.ativar(joao);
        return "HELLO!";
    }
}
