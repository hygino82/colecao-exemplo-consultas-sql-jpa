package br.dev.hygino.colecao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.colecao.dto.AutorDTO;
import br.dev.hygino.colecao.dto.RequestAutorDTO;
import br.dev.hygino.colecao.service.AutorService;


@RestController
@RequestMapping("api/v1/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> buscarPorid(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.autorService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<AutorDTO>> buscarTodos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.autorService.buscarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<AutorDTO> inserirAutor(@RequestBody RequestAutorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.autorService.inserirAutor(dto));
    }  
}
