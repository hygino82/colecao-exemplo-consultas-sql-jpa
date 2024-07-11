package br.dev.hygino.colecao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.dev.hygino.colecao.dto.LivroDTO;
import br.dev.hygino.colecao.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(nativeQuery = true, name = "Livro.buscarPorTituloContendo")
    List<Livro> buscarPorTituloContendo(@Param("titulo") String titulo);

    @Query(name = "Livro.buscarLivroPorId")
    Optional<Livro> buscarPorId(@Param("id") Long id);

    @Query("""
        select obj from Livro obj
        """)
    List<LivroDTO> buscarLivros();
}
