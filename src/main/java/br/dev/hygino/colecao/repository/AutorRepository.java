package br.dev.hygino.colecao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.dev.hygino.colecao.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

        @Query(nativeQuery = true, value = """
                            SELECT * FROM autor WHERE UPPER(nome) LIKE CONCAT('%', UPPER(:nome), '%')
                        """)
        List<Autor> buscarAutorComNomeContendo(String nome);

        @Query("""
                             SELECT obj FROM Autor obj WHERE obj.id = :id
                        """)
        Optional<Autor> buscarAutorPorId(Long id);

        @Query(value = """
                        SELECT obj FROM Autor obj
                        """)
        Page<Autor> buscarTodos(Pageable pageable);
}