package br.dev.hygino.colecao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestLivroDTO(
        @NotBlank @Size(max = 100) String titulo,
        @NotNull Long autorId) {
}
