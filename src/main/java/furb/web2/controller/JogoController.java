package furb.web2.controller;

import furb.web2.model.Jogo;
import furb.web2.service.JogoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")

public class JogoController {

    @Autowired
    private JogoService service;

    // INSERT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public Jogo inserir(
            @RequestBody Jogo jogo) {

        return service.salvar(jogo);
    }

    // SELECT ALL
    @GetMapping

    public List<Jogo> listarTodos() {

        return service.listarTodos();
    }

    // SELECT BY ID
    @GetMapping("/{id}")

    public Jogo buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    // UPDATE
    @PutMapping("/{id}")

    public Jogo atualizar(
            @PathVariable Long id,
            @RequestBody Jogo jogo) {

        return service.atualizar(id, jogo);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deletar(
            @PathVariable Long id) {

        service.deletar(id);
    }
}