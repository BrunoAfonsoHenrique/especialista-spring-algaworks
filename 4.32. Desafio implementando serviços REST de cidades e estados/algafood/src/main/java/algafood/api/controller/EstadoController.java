package algafood.api.controller;

import algafood.domain.model.Estado;
import algafood.domain.respository.EstadoRepository;
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

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return estadoRepository.buscar(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return estadoRepository.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId,
                                            @RequestBody Estado estado) {

        Estado estadoAtual = estadoRepository.buscar(estadoId);

        if(estadoAtual != null) {
            BeanUtils.copyProperties(estado, estadoAtual, "id");

            estadoAtual = estadoRepository.salvar(estadoAtual);

            return ResponseEntity.status(HttpStatus.OK).body(estadoAtual);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
