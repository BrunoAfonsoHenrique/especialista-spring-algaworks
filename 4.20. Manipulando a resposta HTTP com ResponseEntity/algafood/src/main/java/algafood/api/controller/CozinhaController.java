package algafood.api.controller;

import algafood.api.model.CozinhaXmlWrapper;
import algafood.domain.model.Cozinha;
import algafood.domain.respository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    CozinhaRepository cozinhaRepository;

    @GetMapping()
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhaXmlWrapper listarXml() {
        return new CozinhaXmlWrapper(cozinhaRepository.listar());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {
        return cozinhaRepository.buscar(cozinhaId);
    }

}
