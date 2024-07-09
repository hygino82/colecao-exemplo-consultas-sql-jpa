package br.dev.hygino.colecao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.colecao.dto.LivroDTO;
import br.dev.hygino.colecao.entity.Livro;
import br.dev.hygino.colecao.repository.LivroRepository;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;

    @Transactional(readOnly = true)
    public List<LivroDTO> buscarPorTituloContendo(String titulo) {
        var lista = repository.buscarPorTituloContendo(titulo);
        return lista.stream().map(x -> new LivroDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public LivroDTO buscarLivroPorId(Long id) {
        Livro livro = repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe livro com o id: " + id));
        return new LivroDTO(livro);
    }
}
