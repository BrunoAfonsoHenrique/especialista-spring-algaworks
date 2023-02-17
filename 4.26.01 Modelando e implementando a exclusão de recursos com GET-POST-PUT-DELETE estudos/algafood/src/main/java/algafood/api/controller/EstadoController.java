package algafood.api.controller;

import algafood.domain.model.Cozinha;
import algafood.domain.model.Estado;
import algafood.domain.respository.EstadoRepository;
import org.hibernate.PropertyValueException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
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
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Estado estado =  estadoRepository.buscar(estadoId);

        if(estado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(estado);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
        try {
            Estado estadoSalvo = estadoRepository.salvar(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo);

        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId,
                                            @RequestBody Estado estado) {

        Estado estadoAtual = estadoRepository.buscar(estadoId);
        if(estadoAtual != null) {
            BeanUtils.copyProperties(estado, estadoAtual, "id");
            estadoRepository.salvar(estadoAtual);

            return ResponseEntity.status(HttpStatus.OK).body(estadoAtual);
        }

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
