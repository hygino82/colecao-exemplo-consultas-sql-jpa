package br.dev.hygino.colecao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.colecao.dto.AutorDTO;
import br.dev.hygino.colecao.dto.RequestAutorDTO;
import br.dev.hygino.colecao.entity.Autor;
import br.dev.hygino.colecao.repository.AutorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Transactional(readOnly = true)
    public AutorDTO buscarPorId(Long id) {
        final var entity = this.autorRepository
                .buscarAutorPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe autor com o Id: " + id));

        return new AutorDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<AutorDTO> buscarTodos(Pageable pageable) {
        return this.autorRepository.buscarTodos(pageable).map(AutorDTO::new);
    }

    @Transactional
    public AutorDTO inserirAutor(@Valid RequestAutorDTO dto) {
        Autor entity = new Autor();
        dtoToEntity(dto, entity);
        return new AutorDTO(this.autorRepository.save(entity));
    }

    private void dtoToEntity(RequestAutorDTO dto, Autor entity) {
        entity.setNome(dto.nome());
    }

    @Transactional
    public AutorDTO atualizarAutor(Long id, @Valid RequestAutorDTO dto) {
        try {

            Autor entity = this.autorRepository.getReferenceById(id);
            dtoToEntity(dto, entity);
            return new AutorDTO(entity);
        } catch (EntityNotFoundException ex) {
            throw new IllegalArgumentException("Não existe autor com o Id: " + id);
        }
    }

    @Transactional(readOnly = true)
    public Page<AutorDTO> buscarPorNome(String nome, Pageable pageable) {
        return this.autorRepository.buscarPorNome(nome, pageable).map(AutorDTO::new);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void removerAutor(Long id) {
        try {
            this.autorRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Não é possível excluir um autor com livros cadastrados");
        }
    }
}
