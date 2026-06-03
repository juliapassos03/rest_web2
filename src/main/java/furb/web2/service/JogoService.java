package furb.web2.service;

import furb.web2.dto.JogoRequestDTO;
import furb.web2.exception.ParametroObrigatorioException;
import furb.web2.exception.RegistroNaoEncontradoException;
import furb.web2.model.Categoria;
import furb.web2.model.Jogo;
import furb.web2.repository.CategoriaRepository;
import furb.web2.repository.JogoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // INSERT
    public Jogo salvar(JogoRequestDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new ParametroObrigatorioException("O campo 'nome' é obrigatório.");
        }
        Jogo jogo = new Jogo();
        jogo.setNome(dto.getNome());
        jogo.setDescricao(dto.getDescricao());
        jogo.setCategorias(resolverCategorias(dto.getCategoriaIds()));
        return repository.save(jogo);
    }

    // SELECT ALL
    public List<Jogo> listarTodos() {
        return repository.findAll();
    }

    // SELECT BY ID
    public Jogo buscarPorId(Long id) {
        if (id == null) {
            throw new ParametroObrigatorioException("O parâmetro 'id' é obrigatório.");
        }
        return repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Jogo não encontrado com id: " + id));
    }

    // UPDATE
    public Jogo atualizar(Long id, JogoRequestDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new ParametroObrigatorioException("O campo 'nome' é obrigatório.");
        }
        Jogo jogo = buscarPorId(id);
        jogo.setNome(dto.getNome());
        jogo.setDescricao(dto.getDescricao());
        jogo.setCategorias(resolverCategorias(dto.getCategoriaIds()));
        return repository.save(jogo);
    }

    // DELETE
    public void deletar(Long id) {
        Jogo jogo = buscarPorId(id);
        repository.delete(jogo);
    }

    /**
     * Converte uma lista de IDs de categoria em entidades validadas.
     * Lança RegistroNaoEncontradoException se algum ID não existir.
     */
    private List<Categoria> resolverCategorias(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }
        List<Categoria> categorias = new ArrayList<>();
        for (Long categoriaId : ids) {
            Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new RegistroNaoEncontradoException(
                            "Categoria não encontrada com id: " + categoriaId));
            categorias.add(categoria);
        }
        return categorias;
    }
}
