package algafood.api.controller;

import algafood.domain.model.Cidade;
import algafood.domain.respository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{cidades}")
public class CidadeController {

    @Autowired
    CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable Long cidadeId) {
         Cidade cidade = cidadeRepository.buscar(cidadeId);

         if(cidade != null) {
             return ResponseEntity.status(HttpStatus.OK).body(cidade);
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {

    }

}
