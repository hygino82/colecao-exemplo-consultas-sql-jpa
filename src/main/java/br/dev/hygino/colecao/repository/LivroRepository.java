package br.dev.hygino.colecao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.hygino.colecao.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
