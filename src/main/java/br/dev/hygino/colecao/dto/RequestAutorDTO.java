package br.dev.hygino.colecao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestAutorDTO(@NotBlank @Size(max = 50) String nome) {

}
