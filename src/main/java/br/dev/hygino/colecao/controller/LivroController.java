package br.dev.hygino.colecao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.colecao.dto.LivroDTO;
import br.dev.hygino.colecao.service.LivroService;

@RestController
@RequestMapping("livro")
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
}
