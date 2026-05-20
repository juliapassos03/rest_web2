package furb.web2.service;

import furb.web2.model.Jogo;
import furb.web2.repository.JogoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class JogoService {

    @Autowired
    private JogoRepository repository;

    // INSERT
    public Jogo salvar(Jogo jogo) {

        return repository.save(jogo);
    }

    // SELECT ALL
    public List<Jogo> listarTodos() {

        return repository.findAll();
    }

    // SELECT BY ID
    public Jogo buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Jogo não encontrado"));
    }

    // UPDATE
    public Jogo atualizar(Long id,
                          Jogo jogoAtualizado) {

        Jogo jogo = buscarPorId(id);

        jogo.setNome(jogoAtualizado.getNome());
        jogo.setDescricao(jogoAtualizado.getDescricao());
        jogo.setCategorias(jogoAtualizado.getCategorias());

        return repository.save(jogo);
    }

    // DELETE
    public void deletar(Long id) {

        Jogo jogo = buscarPorId(id);

        repository.delete(jogo);
    }
}