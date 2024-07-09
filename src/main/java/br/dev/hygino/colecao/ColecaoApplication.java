package br.dev.hygino.colecao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.dev.hygino.colecao.service.AutorService;
import br.dev.hygino.colecao.service.LivroService;

@SpringBootApplication
public class ColecaoApplication {

	private static final Logger log = LoggerFactory.getLogger(ColecaoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ColecaoApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(LivroService livroService, AutorService autorService) {
		return (arg) -> {
			log.info("");
			log.info("");
			log.info("\nBuscar livro que contenha no titulo");
			for (var livro : livroService.buscarPorTituloContendo("er")) {
				log.info(livro.toString());
			}

			log.info("");
			log.info("");
			log.info("\nBuscar livro por Id");
			var livro = livroService.buscarLivroPorId(1L);
			log.info(livro.toString());
		};
	}
}
