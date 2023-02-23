package algafood.api.controller;

import algafood.domain.exceptions.EntidadeEmUsoException;
import algafood.domain.exceptions.EntidadeNaoEncontradaException;
import algafood.domain.model.Estado;
import algafood.domain.respository.EstadoRepository;
import algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Optional<Estado> estado = estadoRepository.findById(estadoId);

        if(estado.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(estado.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return cadastroEstadoService.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId,
                                            @RequestBody Estado estado) {

        Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);

        if(estadoAtual.isPresent()) {
            BeanUtils.copyProperties(estado, estadoAtual.get(), "id");

            Estado estadoNovo = cadastroEstadoService.salvar(estadoAtual.get());

            return ResponseEntity.status(HttpStatus.OK).body(estadoNovo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> deletar(@PathVariable Long estadoId) {
            try {
                cadastroEstadoService.excluir(estadoId);

                return ResponseEntity.status(HttpStatus.OK).build();
            }
            catch(EntidadeNaoEncontradaException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            catch(EntidadeEmUsoException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }


    }

}
