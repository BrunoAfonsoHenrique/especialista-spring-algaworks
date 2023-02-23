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

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Estado estado = estadoRepository.buscar(estadoId);

        if(estado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(estado);
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

        Estado estadoAtual = estadoRepository.buscar(estadoId);

        if(estadoAtual != null) {
            BeanUtils.copyProperties(estado, estadoAtual, "id");

            estadoAtual = cadastroEstadoService.salvar(estadoAtual);

            return ResponseEntity.status(HttpStatus.OK).body(estadoAtual);
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
