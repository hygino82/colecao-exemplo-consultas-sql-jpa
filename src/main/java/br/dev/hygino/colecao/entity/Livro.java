package br.dev.hygino.colecao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
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
