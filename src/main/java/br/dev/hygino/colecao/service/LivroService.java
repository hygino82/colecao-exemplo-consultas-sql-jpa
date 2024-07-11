package br.dev.hygino.colecao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.colecao.dto.LivroDTO;
import br.dev.hygino.colecao.dto.RequestLivroDTO;
import br.dev.hygino.colecao.entity.Autor;
import br.dev.hygino.colecao.entity.Livro;
import br.dev.hygino.colecao.repository.AutorRepository;
import br.dev.hygino.colecao.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;

    @Autowired
    private AutorRepository autorRepository;

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

    @Transactional(readOnly = true)
    public List<LivroDTO> buscarTodos() {
        return repository.buscarLivros();
    }

    @Transactional
    public LivroDTO inserirLivro(@Valid RequestLivroDTO dto) {
        Livro livroEntity = new Livro();
        Autor autorEntity = this.autorRepository.findById(dto.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Não existe autor com o Id: " + dto.autorId()));
        dtoToEntity(dto, livroEntity, autorEntity);
        return new LivroDTO(this.repository.save(livroEntity));
    }

    private void dtoToEntity(@Valid RequestLivroDTO dto, Livro livroEntity, Autor autorEntity) {
        livroEntity.setTitulo(dto.titulo());
        livroEntity.setAutor(autorEntity);
    }

    @Transactional
    public LivroDTO atualizarLivro(Long id, @Valid RequestLivroDTO dto) {
        Autor autorEntity = this.autorRepository.findById(dto.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Não existe autor com o Id: " + dto.autorId()));

        try {
            Livro livroEntity = this.repository.getReferenceById(id);
            dtoToEntity(dto, livroEntity, autorEntity);
            return new LivroDTO(this.repository.save(livroEntity));
        } catch (EntityNotFoundException ex) {
            throw new IllegalArgumentException("Não existe livro com o Id: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void removerLivro(Long id) {
        this.repository.deleteById(id);
    }
}
