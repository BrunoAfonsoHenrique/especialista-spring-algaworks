package algafood.api.controller;

import algafood.domain.model.Cozinha;
import algafood.domain.respository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

//    @GetMapping("/cozinhas/por-nome")
//    public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nomeCozinha) {
//        return cozinhaRepository.consultarPorNome(nomeCozinha);
//
//    }
}
