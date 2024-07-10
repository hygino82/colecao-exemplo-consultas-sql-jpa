package br.dev.hygino.colecao.entity.projection;

import org.springframework.beans.factory.annotation.Value;

public interface DadosLivro {
    Long getId();

    String getTitulo();

    @Value("#{(target.autor.nome)}")
    String getAutorNome();
}
