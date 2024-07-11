package br.dev.hygino.colecao.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.dev.hygino.colecao.entity.Autor;
import br.dev.hygino.colecao.entity.projections.AutorData;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

        @Query("""
                        SELECT obj FROM Autor obj WHERE UPPER(obj.nome) LIKE CONCAT('%', UPPER(:nome), '%')
                        """)
        Page<Autor> buscarPorNome(String nome, Pageable pageable);

        @Query("""
                             SELECT obj FROM Autor obj WHERE obj.id = :id
                        """)
        Optional<Autor> buscarAutorPorId(Long id);

        @Query(value = """
                        SELECT obj FROM Autor obj
                        """)
        Page<Autor> buscarTodos(Pageable pageable);

        @Query(nativeQuery = true, value = """
                        select * from autor where cod_autor = 1
                        """)
        AutorData teste();
}