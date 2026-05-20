package furb.web2.service;

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

        return repository.save(categoria);
    }

    // SELECT ALL
    public List<Categoria> listarTodas() {

        return repository.findAll();
    }

    // SELECT BY ID
    public Categoria buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoria não encontrada"));
    }

    // UPDATE
    public Categoria atualizar(Long id,
                               Categoria categoriaAtualizada) {

        Categoria categoria = buscarPorId(id);

        categoria.setNome(categoriaAtualizada.getNome());

        return repository.save(categoria);
    }

    // DELETE
    public void deletar(Long id) {

        Categoria categoria = buscarPorId(id);

        repository.delete(categoria);
    }
}