package br.dev.hygino.colecao.dto;

import java.util.List;

import br.dev.hygino.colecao.entity.Autor;

public record AutorDTO(Long id, String nome, List<String> livros) {
    public AutorDTO(Autor entity) {
        this(entity.getId(), entity.getNome(), entity.getLivros().stream().map(x -> x.getTitulo()).toList());
    }
}
