package br.dev.hygino.colecao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.colecao.dto.LivroDTO;
import br.dev.hygino.colecao.dto.RequestLivroDTO;
import br.dev.hygino.colecao.service.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/livro")
public class LivroController {
    @Autowired
    private LivroService service;

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarLivroPorId(id));
    }

    @GetMapping("busca")
    public ResponseEntity<List<LivroDTO>> buscarPorTituloContendo(@RequestParam String titulo) {
        List<LivroDTO> lista = service.buscarPorTituloContendo(titulo);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> buscarTodos() {
        List<LivroDTO> lista = service.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PostMapping
    public ResponseEntity<LivroDTO> inserirLivro(@RequestBody @Valid RequestLivroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserirLivro(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Long id, @RequestBody @Valid RequestLivroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.atualizarLivro(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAutor(@PathVariable Long id) {
        this.service.removerLivro(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
