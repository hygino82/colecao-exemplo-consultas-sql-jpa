package br.dev.hygino.colecao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.colecao.dto.AutorDTO;
import br.dev.hygino.colecao.dto.RequestAutorDTO;
import br.dev.hygino.colecao.entity.Autor;
import br.dev.hygino.colecao.repository.AutorRepository;

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
    public AutorDTO inserirAutor(RequestAutorDTO dto) {
        Autor entity = new Autor();
        dtoToEntity(dto, entity);
        return new AutorDTO(this.autorRepository.save(entity));
    }

    private void dtoToEntity(RequestAutorDTO dto, Autor entity) {
        entity.setNome(dto.nome());
    }
}
