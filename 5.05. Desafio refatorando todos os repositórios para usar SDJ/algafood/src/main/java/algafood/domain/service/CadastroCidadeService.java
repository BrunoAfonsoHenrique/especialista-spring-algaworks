package algafood.domain.service;

import algafood.domain.exceptions.EntidadeEmUsoException;
import algafood.domain.exceptions.EntidadeNaoEncontradaException;
import algafood.domain.model.Cidade;
import algafood.domain.model.Estado;
import algafood.domain.respository.CidadeRepository;
import algafood.domain.respository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe cadastro de estado com código %d", estadoId)));

        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com codigo %d",
                    cidadeId));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de codigo %d não pode ser removida, " +
                    "pois esta em uso", cidadeId));
        }
    }
}
