package furb.web2.controller;

import furb.web2.model.Categoria;
import furb.web2.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    // POST /categorias — INSERT
    @PostMapping
    public ResponseEntity<Categoria> inserir(@RequestBody Categoria categoria) {
        Categoria salva = service.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    // GET /categorias — SELECT ALL
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    // GET /categorias/{id} — SELECT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // PUT /categorias/{id} — UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {
        return ResponseEntity.ok(service.atualizar(id, categoria));
    }

    // DELETE /categorias/{id} — DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
