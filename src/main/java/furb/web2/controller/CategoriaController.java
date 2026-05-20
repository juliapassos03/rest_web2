package furb.web2.controller;

import furb.web2.model.Categoria;
import furb.web2.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")

public class CategoriaController {

    @Autowired
    private CategoriaService service;

    // INSERT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public Categoria inserir(
            @RequestBody Categoria categoria) {

        return service.salvar(categoria);
    }

    // SELECT ALL
    @GetMapping

    public List<Categoria> listarTodas() {

        return service.listarTodas();
    }

    // SELECT BY ID
    @GetMapping("/{id}")

    public Categoria buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    // UPDATE
    @PutMapping("/{id}")

    public Categoria atualizar(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {

        return service.atualizar(id, categoria);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deletar(
            @PathVariable Long id) {

        service.deletar(id);
    }
}