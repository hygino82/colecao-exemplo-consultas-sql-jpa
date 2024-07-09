package br.dev.hygino.colecao.dto;

import br.dev.hygino.colecao.entity.Livro;

public record LivroDTO(Long id, String titulo, String autor) {
    public LivroDTO(Livro entity) {
        this(entity.getId(), entity.getTitulo(), entity.getAutor().getNome());
    }
}
