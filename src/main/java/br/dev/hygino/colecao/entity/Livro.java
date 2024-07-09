package br.dev.hygino.colecao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor

@NamedQuery(name = "Livro.buscarLivroPorId", query = "SELECT obj FROM Livro obj WHERE obj.id = :id")

@NamedNativeQuery(name = "Livro.buscarPorTituloContendo", query = "SELECT * FROM livro WHERE UPPER(titulo_livro) LIKE CONCAT('%',UPPER(:titulo), '%')", resultClass = Livro.class)
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_livro", nullable = false)
    private Long id;

    @Column(name = "titulo_livro", length = 50, nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "cod_autor", nullable = false)
    private Autor autor;
}
