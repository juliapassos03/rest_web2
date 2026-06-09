package furb.web2.controller;

import furb.web2.dto.JogoRequestDTO;
import furb.web2.model.Jogo;
import furb.web2.service.JogoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService service;

    // POST /jogos — INSERT
    @PostMapping
    public ResponseEntity<Jogo> inserir(@RequestBody JogoRequestDTO dto) {
        Jogo salvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /jogos — SELECT ALL
    @GetMapping
    public ResponseEntity<List<Jogo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /jogos/{id} — SELECT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // PUT /jogos/{id} — UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizar(
            @PathVariable Long id,
            @RequestBody JogoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    // DELETE /jogos/{id} — DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
