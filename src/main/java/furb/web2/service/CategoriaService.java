package furb.web2.service;

import furb.web2.exception.ErroIntegridadeException;
import furb.web2.exception.ParametroObrigatorioException;
import furb.web2.exception.RegistroNaoEncontradoException;
import furb.web2.model.Categoria;
import furb.web2.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    // INSERT
    public Categoria salvar(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new ParametroObrigatorioException("O campo 'nome' é obrigatório.");
        }
        return repository.save(categoria);
    }

    // SELECT ALL
    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    // SELECT BY ID
    public Categoria buscarPorId(Long id) {
        if (id == null) {
            throw new ParametroObrigatorioException("O parâmetro 'id' é obrigatório.");
        }
        return repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Categoria não encontrada com id: " + id));
    }

    // UPDATE
    public Categoria atualizar(Long id, Categoria categoriaAtualizada) {
        if (categoriaAtualizada.getNome() == null || categoriaAtualizada.getNome().isBlank()) {
            throw new ParametroObrigatorioException("O campo 'nome' é obrigatório.");
        }
        Categoria categoria = buscarPorId(id);
        categoria.setNome(categoriaAtualizada.getNome());
        return repository.save(categoria);
    }

    // DELETE
    public void deletar(Long id) {
        Categoria categoria = buscarPorId(id);
        // Como o relacionamento é @ManyToMany(mappedBy=...), o JPA não lança
        // DataIntegrityViolationException ao deletar a categoria — verificamos manualmente.
        if (categoria.getJogos() != null && !categoria.getJogos().isEmpty()) {
            throw new ErroIntegridadeException(
                    "Não é possível excluir a categoria '" + categoria.getNome() +
                    "' pois ela está associada a " + categoria.getJogos().size() + " jogo(s).");
        }
        repository.delete(categoria);
    }
}
